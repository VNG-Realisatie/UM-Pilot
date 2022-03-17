package com.cap.pocvng.repository;

import com.cap.pocvng.entity.AanvraagWerkzoekende;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;


public interface AanvraagWerkzoekendeRepository extends JpaRepository<AanvraagWerkzoekende, String> {

    List<AanvraagWerkzoekende> findAllByCreatieDatumBefore(LocalDate expirydate);

    List<AanvraagWerkzoekende> findByOin(String oin);

    AanvraagWerkzoekende findByVraagIdAndOin(String vraagId, String oin);
}
