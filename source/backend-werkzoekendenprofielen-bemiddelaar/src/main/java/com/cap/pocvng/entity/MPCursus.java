package com.cap.pocvng.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * MPCursus
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@Embeddable
@Schema(description = "De cursus die de werkzoekende volgt, heeft gevolgd of heeft ingepland om te gaan volgen.")
public class MPCursus {

    @Size(max = 120)
    @Schema(description = "De naam van de cursus zoals deze bekend staat.")
    private String naamCursus;

    @Schema(description = "De datum van de dag die op het certificaat staat.")
    private LocalDate datumCertificaat;

}
