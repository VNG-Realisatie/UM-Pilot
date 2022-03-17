package com.cap.pocvng.service;

import com.cap.pocvng.entity.Vacature;
import com.cap.pocvng.repository.VacatureRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GemeenteService {

    private final VacatureRepository repository;

    public GemeenteService(VacatureRepository repository) {
        this.repository = repository;
    }

    /**
     * Save all Vacature from the list into the DB.
     *
     * @param vacatures list of Vacature to be saved in the DB.
     * @param oin       identifier of municipality of Vacature.
     * @return List of Vacature entities after being saved in the DB.
     */
    public List<Vacature> saveAll(List<Vacature> vacatures, String oin) {
        // first delete all vacatures
        repository.deleteAll();
        vacatures.forEach(x -> {
            //Add OIN to each vacature before saving to DB
            x.setOin(oin);
        });
        return repository.saveAll(vacatures);
    }

    public List<Vacature> findAll(String oin) {
        return repository.findAllByOin(oin);
    }
}
