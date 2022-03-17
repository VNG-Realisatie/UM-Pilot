package com.cap.pocvng.entity;

import com.cap.pocvng.validator.OneOfInt;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToOne;
import javax.validation.Valid;

/**
 * Opleiding
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Opleiding {

    @OneOfInt({1, 2, 3, 4, 5, 6, 9})
    @Schema(description = "De code die het niveau van de OPLEIDING aangeeft.\n\n" +
            "- 1 = Basisonderwijs\n" +
            "- 2 = VMBO\n" +
            "- 3 = HAVO/VWO\n" +
            "- 4 = MBO\n" +
            "- 5 = HBO/Bachelor\n" +
            "- 6 = WO/Master\n" +
            "- 9 = Anders",
            type = "Integer",
            allowableValues = {"1", "2", "3", "4", "5", "6", "9"})
    private Integer codeNiveauOpleiding;

    @OneOfInt({0, 1, 2, 8})
    @Schema(description = "Indicatie die aangeeft of voor de OPLEIDING een diploma, een getuigschrift of één of meer (deel-)certifica(a)t(en) is behaald.\n\n" +
            "- 0 = Onbekend\n" +
            "- 1 = Ja\n" +
            "- 2 = Nee\n" +
            "- 8 = Niet van toepassing",
            type = "Integer",
            allowableValues = {"0", "1", "2", "8"}
    )
    private Integer indicatieDiploma;

    @Valid
    @OneToOne(cascade = {CascadeType.ALL})
    @Schema(description = "De naamgegevens van een OPLEIDING.\n\n" +
            "Norminstantie: SGR")
    private Opleidingsnaam opleidingsnaam;
}
