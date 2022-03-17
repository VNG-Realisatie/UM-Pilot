package com.cap.pocvng.entity;

import com.cap.pocvng.validator.OneOfString;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

/**
 * Rijbewijs
 */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Schema(description = "Geautoriseerd document om een motorvoertuig over de weg te mogen besturen.\n\n" +
        "Norminstantie: SGR")
public class Rijbewijs {

    @Size(max = 3)
    @OneOfString({
            "A", "A1", "A2", "AM", "B", "B+", "C1", "C", "D1", "D", "BE", "C1E", "CE", "D1E", "DE", "T"
    })
    @Schema(description = "De code die de rijbewijscategorie aangeeft.\n\n" +
            "- A = Zware motor met onbeperkt vermogen\n" +
            "- A1 = Lichte motor, 125 cc en maximaal vermogen van 11 kilowatt (kW)\n" +
            "- A2 = Middelzware motor, Maximaal vermogen van 35 kilowatt (kW)\n" +
            "- AM = Bromfiets en snorfiets\n" +
            "- B = Auto\n" +
            "- B+ = Auto, eventueel met aanhangwagen van meer dan 750kg met een maximum van 4250 kilo (auto + aanhanger)\n" +
            "- C1 = Lichte vrachtwagen met een maximaal gewicht van 7500kg, eventueel met aanhangwagen tot 750kg\n" +
            "- C = Vrachtwagen met een gewicht van meer dan 3500kg, eventueel met aanhangwagen tot 750kg\n" +
            "- D1 = Bus voor meer dan 8 en maximaal 16 zitplaatsen, exclusief de bestuurder). Maximale lengte bus 8 meter.\n" +
            "- D = Bus voor meer dan 8 personen, eventueel met aanhangwagen tot 750kg\n" +
            "- BE = Auto, eventueel met aanhangwagen van meer dan 750kg met een maximum van 3500 kilo (auto + aanhanger)\n" +
            "- C1E = Lichte vrachtwagen met een maximaal gewicht van 7500kg, eventueel met aanhangwagen van meer dan 750kg\n" +
            "- CE = Vrachtwagen met een gewicht van meer dan 3500kg, eventueel met aanhangwagen van meer dan 750kg\n" +
            "- D1E = Bus voor maximaal 16 personen excl. bestuurder, eventueel met aanhangwagen van meer dan 750kg\n" +
            "- DE = Bus voor meer dan 8 personen, eventueel met aanhangwagen van meer dan 750kg\n" +
            "- T = Trekker",
            type = "String",
            allowableValues = {"A", "A1", "A2", "AM", "B", "B+", "C1", "C", "D1", "D", "BE", "C1E", "CE", "D1E", "DE", "T"})
    private String codeSoortRijbewijs;

}
