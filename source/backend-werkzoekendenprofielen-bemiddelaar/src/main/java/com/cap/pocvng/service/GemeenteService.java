package com.cap.pocvng.service;

import com.cap.pocvng.config.ConfigProperties;
import com.cap.pocvng.dto.InlineResponse200;
import com.cap.pocvng.dto.InlineResponse2001;
import com.cap.pocvng.dto.WerkzoekendeProfielMatchesRequest;
import com.cap.pocvng.dto.WerkzoekendeProfielMatchesRequestGemeente;
import com.cap.pocvng.entity.AanvraagWerkzoekende;
import com.cap.pocvng.entity.MPWerkzoekendeMatch;
import com.cap.pocvng.entity.Werkzoekende;
import com.cap.pocvng.exception.NoMoreAccessRequestsLeftException;
import com.cap.pocvng.exception.UnprocessableException;
import com.cap.pocvng.exception.VraagIdAlreadyInDatabaseException;
import com.cap.pocvng.exception.VraagIdNotFoundException;
import com.cap.pocvng.mapper.SimpleMapper;
import com.cap.pocvng.repository.AanvraagWerkzoekendeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class GemeenteService {

    private final AanvraagWerkzoekendeRepository repository;
    private final RestTemplate restTemplate;
    private final SimpleMapper mapper;
    private final ConfigProperties properties;

    public GemeenteService(AanvraagWerkzoekendeRepository repository, RestTemplate restTemplate, SimpleMapper mapper, ConfigProperties properties) {
        this.repository = repository;
        this.restTemplate = restTemplate;
        this.mapper = mapper;
        this.properties = properties;
    }


    public List<AanvraagWerkzoekende> findAll(String oin) {
        return repository.findByOin(oin);
    }


    public AanvraagWerkzoekende findByVraagIdAndOin(String vraagId, String oin) {
        AanvraagWerkzoekende aanvraagInDb = repository.findByVraagIdAndOin(vraagId, oin);
        if (aanvraagInDb == null) {
            throw new VraagIdNotFoundException();
        } else {
            return aanvraagInDb;
        }
    }

    /**
     * Make a rest call to VUM with the aanvraagWerkzoekende. Subsequently save the response vraag id in the database.
     *
     * @param aanvraagWerkzoekende AanvraagWerkzoekende to match on
     * @param oin                  identifier of municipality
     * @return the response received from VUM
     */
    public InlineResponse200 makeRequestMatchesVum(WerkzoekendeProfielMatchesRequestGemeente aanvraagWerkzoekende, String oin) {
        WerkzoekendeProfielMatchesRequest request = mapper.requestGemeenteToWerkzoekendeProfielMatchesRequest(aanvraagWerkzoekende);
        // Add callbackURL to request.
        request.setCallbackURL(properties.getCallbackUrl());
        InlineResponse200 response = null;
        try {
            response = restTemplate.postForObject(properties.getVumUrlMatches(), request, InlineResponse200.class); // TODO: add headers
            createVraagIdAanvraag(Objects.requireNonNull(response).getVraagID(), oin, aanvraagWerkzoekende.getAanvraagKenmerk());
        } catch (VraagIdAlreadyInDatabaseException e) {
            log.error("Vraag id already in database");
            throw new UnprocessableException("Vraag id bestaat al in database");
        } catch (RestClientException e) {
            log.error("Rest client error in response from VUM after request for matches." + e.getMessage());
            throw new UnprocessableException("Rest client error");
        } catch (NullPointerException e) {
            log.error("NullPointer response from VUM after request for matches." + e.getMessage());
            throw new UnprocessableException("Rest client error");
        }
        return response;
    }

    /**
     * Create a AanvraagWerkzoekende with given vraagID and OIN.
     *
     * @param vraagId vraag id to save in the DB
     * @param oin     identifier of municipality
     * @throws VraagIdAlreadyInDatabaseException if VraagID is already in the DB.
     */
    private void createVraagIdAanvraag(String vraagId, String oin, String aanvraagKenmerk) throws VraagIdAlreadyInDatabaseException {
        if (repository.existsById(vraagId)) {
            throw new VraagIdAlreadyInDatabaseException();
        }
        repository.save(new AanvraagWerkzoekende(vraagId, oin, aanvraagKenmerk, properties.getMaxTimesAccessible()));
    }

    /**
     * Make a rest call to VUM for a detail werkzoekende.
     *
     * @param vraagId - identifier of the initial request
     * @param vumId   - identifier of the werkzoekende
     * @param oin     - identifier of municipality
     * @return the response received from VUM
     */
    public Werkzoekende makeRequestDetailWerkzoekende(String vraagId, String vumId, String oin) {
        AanvraagWerkzoekende aanvraagInDb = findByVraagIdAndOin(vraagId, oin);
        try {
            checkTimesAccessLeft(aanvraagInDb);
//            InlineResponse2001 response = restTemplate.getForObject(properties.getVumUrlVumId() + vumId, InlineResponse2001.class);
            InlineResponse2001 response = restTemplate.getForObject(properties.getVumUrlVumId(), InlineResponse2001.class); //TODO: remove only for demo
            decreaseTimesAccessLeft(aanvraagInDb, vumId);
            markFetched(aanvraagInDb, vumId);
            return Objects.requireNonNull(response).getWerkzoekende();
        } catch (RestClientException | NullPointerException e) {
            log.error("Error in response from VUM after request for detailed werkzoekende.");
            throw new UnprocessableException("Rest client error");
        }
    }

    // Utility method to check if AanvraagWerkzoekende still has request left
    private void checkTimesAccessLeft(AanvraagWerkzoekende vraagIdInDb) {
        if (vraagIdInDb.getTimesAccessLeft() <= 0) {
            throw new NoMoreAccessRequestsLeftException();
        }
    }

    // Utility method to decrease requests left of AanvraagWerkzoekende
    private void decreaseTimesAccessLeft(AanvraagWerkzoekende vraagIdInDb, String vumId) {
        MPWerkzoekendeMatch werkzoekende = vraagIdInDb.getWerkzoekenden()
                .stream()
                .filter(mpWerkzoekendeMatch -> mpWerkzoekendeMatch.getVumID().equals(vumId))
                .findAny()
                .orElse(null);

        if (werkzoekende != null && werkzoekende.isFetchedDetail()) {
            return;
        }
        checkTimesAccessLeft(vraagIdInDb);

        vraagIdInDb.setTimesAccessLeft(vraagIdInDb.getTimesAccessLeft() - 1);
        repository.save(vraagIdInDb);
    }

    // Utility method to mark a werkzoekende as fetched
    private void markFetched(AanvraagWerkzoekende vraagIdInDb, String vumId) {
        vraagIdInDb.getWerkzoekenden().forEach(werkzoekende -> {
            if (werkzoekende.getVumID().equals(vumId)) {
                werkzoekende.setFetchedDetail(true);
            }
        });
        repository.save(vraagIdInDb);
    }

    /**
     * Remove all expired aanvragen from the specified days in the application properties.
     */
    public void removeAllExpiredAanvraag() {
        LocalDate expirydate = LocalDate.now().minusDays(properties.getDaysToExpiry());
        List<AanvraagWerkzoekende> expiredAanvragen = repository.findAllByCreatieDatumBefore(expirydate);
        repository.deleteAll(expiredAanvragen);
    }

}
