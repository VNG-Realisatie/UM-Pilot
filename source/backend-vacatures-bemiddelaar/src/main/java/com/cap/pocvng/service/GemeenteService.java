package com.cap.pocvng.service;

import com.cap.pocvng.config.ConfigProperties;
import com.cap.pocvng.dto.InlineResponse200;
import com.cap.pocvng.dto.InlineResponse2001;
import com.cap.pocvng.dto.VacatureMatchesRequest;
import com.cap.pocvng.dto.VacatureMatchesRequestGemeente;
import com.cap.pocvng.entity.AanvraagVacature;
import com.cap.pocvng.entity.MPVacatureMatch;
import com.cap.pocvng.entity.Vacature;
import com.cap.pocvng.exception.NoMoreAccessRequestsLeftException;
import com.cap.pocvng.exception.UnprocessableException;
import com.cap.pocvng.exception.VraagIdAlreadyInDatabaseException;
import com.cap.pocvng.exception.VraagIdNotFoundException;
import com.cap.pocvng.mapper.SimpleMapper;
import com.cap.pocvng.repository.AanvraagVacatureRepository;
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

    private final AanvraagVacatureRepository repository;
    private final RestTemplate restTemplate;
    private final SimpleMapper mapper;
    private final ConfigProperties properties;

    public GemeenteService(AanvraagVacatureRepository repository,
                           RestTemplate restTemplate,
                           SimpleMapper mapper,
                           ConfigProperties properties) {
        this.repository = repository;
        this.restTemplate = restTemplate;
        this.mapper = mapper;
        this.properties = properties;
    }


    public List<AanvraagVacature> findAll(String oin) {
        return repository.findByOin(oin);
    }

    public AanvraagVacature findByVraagIdAndOin(String vraagId, String oin) {
        AanvraagVacature aanvraagInDb = repository.findByVraagIdAndOin(vraagId, oin);
        if (aanvraagInDb == null) {
            throw new VraagIdNotFoundException();
        } else {
            return aanvraagInDb;
        }
    }

    /**
     * Make a rest call to VUM with the aanvraagVacature. Subsequently save the response vraag id in the database.
     *
     * @param aanvraagVacature aanvraagVacature to match on
     * @param oin              identifier of municipality
     * @return the response received from VUM
     */
    public InlineResponse200 makeRequestMatchesVum(VacatureMatchesRequestGemeente aanvraagVacature, String oin) {
        VacatureMatchesRequest request = mapper.vacatureMatchesRequestGemeenteToVacatureMatchesRequest(aanvraagVacature);
        // Add callbackURL to request.
        request.setCallbackURL(properties.getCallbackUrl());
        InlineResponse200 response = null;
        try {
            response = restTemplate.postForObject(properties.getVumUrlMatches(), request, InlineResponse200.class); // TODO: add headers
            createVraagIdAanvraag(Objects.requireNonNull(response).getVraagID(), oin, aanvraagVacature.getAanvraagKenmerk());
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
     * Create a AanvraagVacature with given vraagID and OIN.
     *
     * @param vraagId vraag id to save in the DB
     * @param oin     identifier of municipality
     * @throws VraagIdAlreadyInDatabaseException if VraagID is already in the DB.
     */
    private void createVraagIdAanvraag(String vraagId, String oin, String aanvraagKenmerk) throws VraagIdAlreadyInDatabaseException {
        if (repository.existsById(vraagId)) {
            throw new VraagIdAlreadyInDatabaseException();
        }
        repository.save(new AanvraagVacature(vraagId, oin, aanvraagKenmerk, properties.getMaxTimesAccessible()));
    }

    /**
     * Make a rest call to VUM for a detail vacature.
     *
     * @param vraagId - identifier of the initial request
     * @param vumId   - identifier of the vacature
     * @param oin     - identifier of municipality
     * @return the response received from VUM
     */
    public Vacature makeRequestDetailVacature(String vraagId, String vumId, String oin) {
        AanvraagVacature aanvraagInDb = findByVraagIdAndOin(vraagId, oin);
        try {
            checkTimesAccessLeft(aanvraagInDb);
//            InlineResponse2001 response = restTemplate.getForObject(properties.getVumUrlVumId() + vumId, InlineResponse2001.class);
            InlineResponse2001 response = restTemplate.getForObject(properties.getVumUrlVumId(), InlineResponse2001.class); //TODO: remove only for demo
            decreaseTimesAccessLeft(aanvraagInDb, vumId); //TODO: does VUM return error http when too much request, what if request fails should not decrease
            markFetched(aanvraagInDb, vumId);
            return Objects.requireNonNull(response).getVacature();
        } catch (RestClientException | NullPointerException e) {
            log.error("Error in response from VUM after request for detailed vacature.");
            throw new UnprocessableException("Rest client error");
        }
    }

    // Utility method to check if AanvraagVacature still has request left
    private void checkTimesAccessLeft(AanvraagVacature vraagIdInDb) {
        if (vraagIdInDb.getTimesAccessLeft() <= 0) {
            throw new NoMoreAccessRequestsLeftException();
        }
    }

    // Utility method to decrease requests left of AanvraagVacature
    private void decreaseTimesAccessLeft(AanvraagVacature vraagIdInDb, String vumId) {
        MPVacatureMatch vacature = vraagIdInDb.getVacatures()
                .stream()
                .filter(mpVacatureMatch -> mpVacatureMatch.getVumID().equals(vumId))
                .findAny()
                .orElse(null);

        if (vacature != null && vacature.isFetchedDetail()) {
            return;
        }
        checkTimesAccessLeft(vraagIdInDb);

        vraagIdInDb.setTimesAccessLeft(vraagIdInDb.getTimesAccessLeft() - 1);
        repository.save(vraagIdInDb);
    }

    // Utility method to mark a vacature as fetched
    private void markFetched(AanvraagVacature vraagIdInDb, String vumId) {
        vraagIdInDb.getVacatures().forEach(vacature -> {
            if (vacature.getVumID().equals(vumId)) {
                vacature.setFetchedDetail(true);
            }
        });
        repository.save(vraagIdInDb);
    }


    /**
     * Remove all expired aanvragen from the specified days in the application properties.
     */
    public void removeAllExpiredAanvraag() {
        LocalDate expirydate = LocalDate.now().minusDays(properties.getDaysToExpiry());
        List<AanvraagVacature> expiredAanvragen = repository.findAllByCreatieDatumBefore(expirydate);
        repository.deleteAll(expiredAanvragen);
    }


}

