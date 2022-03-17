package com.cap.pocvng.entity;

import com.cap.pocvng.validator.OneOfInt;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * Gedragscompetentie
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Schema(description = "De karaktereigenschappen van een natuurlijk persoon.")
public class Gedragscompetentie {

    @OneOfInt({24100, 24101, 24102, 24104, 24105, 24106, 24107, 24108, 24109, 24110, 24111, 24112, 24113,
            24114, 24115, 24116, 24118, 24119, 24120, 24121, 24122, 24123, 24124, 24125, 24126})
    @Schema(description = "De code die de GEDRAGSCOMPETENTIE aangeeft.\n\n" +
            "- 24100 = Beslissen en activiteiten initiëren\n" +
            "- 24101 = Aansturen\n" +
            "- 24102 = Begeleiden\n" +
            "- 24104 = Aandacht en begrip tonen\n" +
            "- 24105 = Samenwerken en overleggen\n" +
            "- 24106 = Ethisch en integer handelen\n" +
            "- 24107 = Relaties bouwen en netwerken\n" +
            "- 24108 = Overtuigen en beïnvloeden\n" +
            "- 24109 = Presenteren\n" +
            "- 24110 = Formuleren en rapporteren\n" +
            "- 24111 = Vakdeskundigheid toepassen\n" +
            "- 24112 = Materialen en middelen inzetten\n" +
            "- 24113 = Analyseren\n" +
            "- 24114 = Onderzoeken\n" +
            "- 24115 = Creeren en innoveren\n" +
            "- 24116 = Leren\n" +
            "- 24118 = Plannen en organiseren\n" +
            "- 24119 = Op de behoeften en verwachtingen van de klant richten\n" +
            "- 24120 = Kwaliteit leveren\n" +
            "- 24121 = Instructies en procedures opvolgen\n" +
            "- 24122 = Omgaan met verandering en aanpassen\n" +
            "- 24123 = Met druk en tegenslag omgaan\n" +
            "- 24124 = Gedrevenheid en ambitie tonen\n" +
            "- 24125 = Ondernemend en commercieel handelen\n" +
            "- 24126 = Bedrijfsmatig handelen",
            type = "Integer",
            allowableValues = {"24100", "24101", "24102", "24104", "24105", "24106", "24107", "24108", "24109", "24110", "24111", "24112", "24113",
                    "24114", "24115", "24116", "24118", "24119", "24120", "24121", "24122", "24123", "24124", "24125", "24126"})
    private Integer codeGedragscompetentie;

    @Size(max = 120)
    @Schema(description = "De omschrijving van de GEDRAGSCOMPETENTIE.")
    private String omschrijvingGedragscompetentie;

    @OneOfInt({1, 2, 3, 4, 5, 9})
    @Schema(description = "De code die aangeeft in welke mate de gedragscompetentie beheerst wordt.\n\n" +
            "- 1 = Goed\n" +
            "- 2 = Voldoende\n" +
            "- 3 = Onvoldoende\n" +
            "- 4 = Niet\n" +
            "- 5 = Onderzoek noodzakelijk\n" +
            "- 9 = Niet van toepassing",
            type = "Integer",
            allowableValues = {"1", "2", "3", "4", "5", "9"}
    )
    private Integer codeBeheersingGedragscompetentie;

}
