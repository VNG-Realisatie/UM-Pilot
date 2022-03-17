package com.cap.pocvng.repository;

import com.cap.pocvng.entity.Vacature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface VacatureRepository extends JpaRepository<Vacature, String>, JpaSpecificationExecutor<Vacature> {


    Optional<Vacature> findByIdVacatureAndOin(String idVacature, String oin);

    List<Vacature> findAllByOin(String oin);
}
