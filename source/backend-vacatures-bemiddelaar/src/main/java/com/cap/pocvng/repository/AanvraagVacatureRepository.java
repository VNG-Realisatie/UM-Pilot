package com.cap.pocvng.repository;

import com.cap.pocvng.entity.AanvraagVacature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AanvraagVacatureRepository extends JpaRepository<AanvraagVacature, String> {

    List<AanvraagVacature> findAllByCreatieDatumBefore(LocalDate expirydate);

    List<AanvraagVacature> findByOin(String oin);

    AanvraagVacature findByVraagIdAndOin(String vraagId, String oin);

}
