package com.cap.pocvng.service;

import com.cap.pocvng.entity.Werkzoekende;
import com.cap.pocvng.repository.WerkzoekendeRepository;
import org.bouncycastle.jcajce.provider.digest.Keccak;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class GemeenteService {

    private final WerkzoekendeRepository repository;

    @Autowired
    public GemeenteService(WerkzoekendeRepository repository) {
        this.repository = repository;
    }

    /**
     * Save all Werkzoekende from the list into the DB.
     *
     * @param werkzoekenden list of Werkzoekende to be saved in the DB.
     * @param oin           identifier of municipality of Werkzoekende.
     * @return List of Werkzoekende entities after being saved in the DB.
     */
    public List<Werkzoekende> saveAll(List<Werkzoekende> werkzoekenden, String oin) {
        // first delete all users
        repository.deleteAll();
        werkzoekenden.forEach(x -> {
            //Add OIN to each werkzoekende before saving to DB
            x.setOin(oin);
            //Hash ID before saving to DB
            x.setIdWerkzoekende(hashId(x.getIdWerkzoekende()));
        });
        return repository.saveAll(werkzoekenden);
    }

    private String hashId(String idWerkzoekende) {
        Keccak.Digest256 digest256 = new Keccak.Digest256();
        byte[] hashbytes = digest256.digest(
                idWerkzoekende.getBytes(StandardCharsets.UTF_8));
        return new String(Hex.encode(hashbytes));
    }


    public List<Werkzoekende> findAll(String oin) {
        return repository.findAllByOin(oin);
    }
}
