package com.cap.pocvng.repository;

import com.cap.pocvng.entity.Werkzoekende;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface WerkzoekendeRepository extends JpaRepository<Werkzoekende, String>, JpaSpecificationExecutor<Werkzoekende> {

    Optional<Werkzoekende> findByIdWerkzoekendeAndOin(String idWerkzoekende, String oin);

    List<Werkzoekende> findAllByOin(String oin);
}
