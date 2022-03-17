package com.cap.pocvng.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.validation.Valid;
import javax.validation.constraints.Size;

/**
 * ContactpersoonAfdeling
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ContactpersoonAfdeling {

    @Size(max = 35)
    @Schema(description = "De naam van de CONTACTPERSOON/-AFDELING")
    private String naamContactpersoonAfdeling;

    @Embedded
    @Valid
    private Telefoonnummer telefoonnummer;

    @Embedded
    @Valid
    private Emailadres emailadres;


}
