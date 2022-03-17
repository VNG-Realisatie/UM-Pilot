package com.cap.pocvng.service;

import com.cap.pocvng.dto.VacatureMatchesCallbackRequest;
import com.cap.pocvng.entity.AanvraagVacature;
import com.cap.pocvng.entity.MPVacatureMatch;
import com.cap.pocvng.exception.VraagIdNotFoundException;
import com.cap.pocvng.repository.AanvraagVacatureRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VumService {

    private final AanvraagVacatureRepository repository;

    public VumService(AanvraagVacatureRepository repository) {
        this.repository = repository;
    }

    /**
     * Handle callback. Either the AanvraagVacature is already in the DB or it still has to be added.
     *
     * @param callbackRequest
     */
    public void handleCallback(VacatureMatchesCallbackRequest callbackRequest) {
        Optional<AanvraagVacature> aanvraagInDbOpt = repository.findById(callbackRequest.getVraagID());
        if (aanvraagInDbOpt.isEmpty()) {
            throw new VraagIdNotFoundException();
        } else {
            AanvraagVacature aanvraagInDb = aanvraagInDbOpt.get();
            for (MPVacatureMatch vacature : callbackRequest.getMatches().getMpVacatureMatches()) {
                aanvraagInDb.addVacature(vacature);
            }
            repository.save(aanvraagInDb);
        }
    }


}
