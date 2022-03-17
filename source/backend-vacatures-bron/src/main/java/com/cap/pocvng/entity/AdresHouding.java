package com.cap.pocvng.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * AdresHouding
 */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Schema(description = "De gegevens over de functie van het adres en de geldigheidsdata van de adreshouding" +
        " ten opzichte van de PERSOON of de ONDERNEMING/INSTELLING.\n\n" +
        "Norminstantie: SGR")
public class AdresHouding {

    @Size(max = 1)
    @Schema(description = "De code die aangeeft wat de functie van het adres is, welke is gerelateerd aan de PERSOON " +
            "of de ONDERNEMING/INSTELLING binnen een bepaalde periode.\n\n" +
            "- B = Briefadres (BRP)\n" +
            "- W = Woonadres (BRP)\n" +
            "- C = Correspondentieadres\n" +
            "- L = Loonaangifte adres\n" +
            "- A = Afwijkend woonadres\n" +
            "- V = Vestigingsadres\n" +
            "- E = Werklocatieadres")
    private String codeFunctieAdres;

    @Schema(description = "De datum van de eerste dag waarop het adres gerelateerd is aan de PERSOON of de ONDERNEMING/INSTELLING.")
    private LocalDate datumAanvangAdres;

    @Schema(description = "De datum van de laatste dag waarop het adres gerelateerd is aan de PERSOON of de ONDERNEMING/INSTELLING.")
    private LocalDate datumEindeAdres;

    @Valid
    @OneToOne(cascade = {CascadeType.ALL})
    private Adres adres;

}
