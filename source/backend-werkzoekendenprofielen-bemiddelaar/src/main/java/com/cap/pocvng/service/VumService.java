package com.cap.pocvng.service;

import com.cap.pocvng.dto.WerkzoekendeProfielMatchesCallbackRequest;
import com.cap.pocvng.entity.AanvraagWerkzoekende;
import com.cap.pocvng.entity.MPWerkzoekendeMatch;
import com.cap.pocvng.exception.VraagIdNotFoundException;
import com.cap.pocvng.repository.AanvraagWerkzoekendeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VumService {

    private final AanvraagWerkzoekendeRepository repository;

    public VumService(AanvraagWerkzoekendeRepository repository) {
        this.repository = repository;
    }

    /**
     * Handle callback. Either the AanvraagWerkzoekende is already in the DB or it still has to be added.
     *
     * @param matchesRequest - callback request received from VUM
     */
    public void handleCallback(WerkzoekendeProfielMatchesCallbackRequest matchesRequest) {
        Optional<AanvraagWerkzoekende> aanvraagInDbOpt = repository.findById(matchesRequest.getVraagID());
        if (aanvraagInDbOpt.isEmpty()) {
            throw new VraagIdNotFoundException();
        } else {
            AanvraagWerkzoekende aanvraagInDb = aanvraagInDbOpt.get();
            for (MPWerkzoekendeMatch werkzoekendeMatch : matchesRequest.getMatches().getMpWerkzoekendeMatches()) {
                aanvraagInDb.addWerkzoekende(werkzoekendeMatch);
            }
            repository.save(aanvraagInDb);
        }
    }
}
