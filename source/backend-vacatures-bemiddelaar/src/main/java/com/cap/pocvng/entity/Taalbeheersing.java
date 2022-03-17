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
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Taalbeheersing
 */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Schema(description = "De mate waarin een natuurlijk persoon een taal beheerst.")
public class Taalbeheersing {

    @Size(max = 3)
    @Pattern(regexp = "^[a-z]{3}$")
    @Schema(description = "De code die de taal aangeeft. Betreft ISO 639-3 codelijst")
    private String codeTaal;

    @OneOfInt({0, 1, 2, 3, 4, 8})
    @Schema(description = "Code die de mate aangeeft waarin de natuurlijk persoon de betreffende taal mondeling machtig is.\n\n" +
            "- 0 = Onbekend\n" +
            "- 1 = Geen beheersing\n" +
            "- 2 = Redelijk\n" +
            "- 3 = Goed\n" +
            "- 4 = Uitstekend\n" +
            "- 8 = Niet van toepassing",
            type = "Integer",
            allowableValues = {"0", "1", "2", "3", "4", "8"})
    private Integer codeNiveauTaalbeheersingMondeling;

    @OneOfInt({0, 1, 2, 3, 4, 8})
    @Schema(description = "Code die de mate aangeeft waarin de natuurlijk persoon de betreffende taal schriftelijk machtig is.\n\n" +
            "- 0 = Onbekend\n" +
            "- 1 = Geen beheersing\n" +
            "- 2 = Redelijk\n" +
            "- 3 = Goed\n" +
            "- 4 = Uitstekend\n" +
            "- 8 = Niet van toepassing",
            type = "Integer",
            allowableValues = {"0", "1", "2", "3", "4", "8"})
    private Integer codeNiveauTaalbeheersingSchriftelijk;

    @OneOfInt({0, 1, 2, 3, 4, 8})
    @Schema(description = "Code die die het niveau van de leesvaardigheid van de natuurlijk persoon in de betreffende taal aangeeft.\n\n" +
            "- 0 = Onbekend\n" +
            "- 1 = Geen beheersing\n" +
            "- 2 = Redelijk\n" +
            "- 3 = Goed\n" +
            "- 4 = Uitstekend\n" +
            "- 8 = Niet van toepassing",
            type = "Integer",
            allowableValues = {"0", "1", "2", "3", "4", "8"})
    private Integer codeNiveauTaalbeheersingLezen;

    @OneOfInt({0, 1, 2, 3, 4, 8})
    @Schema(description = "Code die het niveau van luistervaardigheid van de natuurlijk persoon in de betreffende taal aangeeft.\n\n" +
            "- 0 = Onbekend\n" +
            "- 1 = Geen beheersing\n" +
            "- 2 = Redelijk\n" +
            "- 3 = Goed\n" +
            "- 4 = Uitstekend\n" +
            "- 8 = Niet van toepassing",
            type = "Integer",
            allowableValues = {"0", "1", "2", "3", "4", "8"})
    private Integer codeNiveauTaalbeheersingLuisteren;


}
