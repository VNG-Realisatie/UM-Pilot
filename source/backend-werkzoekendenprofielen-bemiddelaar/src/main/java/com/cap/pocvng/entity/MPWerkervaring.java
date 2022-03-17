package com.cap.pocvng.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * MPWerkervaring
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@Embeddable
@Schema(description = "De werkzaamheden die een natuurlijk persoon in een bepaalde periode als beroep heeft uitgevoerd.")
public class MPWerkervaring {

    @Schema(description = "De datum van de eerste dag van de werkzaamheden.")
    private LocalDate datumAanvangWerkzaamheden;

    @Schema(description = "De datum van de laatste dag van de werkzaamheden.")
    private LocalDate datumEindeWerkzaamheden;

    @Size(max = 70)
    @Schema(description = "De naam waaronder de organisatie bekend is.")
    private String naamOrganisatie;

    @Valid
    @OneToOne(cascade = {CascadeType.ALL})
    private Beroepsnaam beroep;

}
