package com.cap.pocvng.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * Opleiding
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "De scholing die de werkzoekende volgt of heeft gevolgd.")
public class Opleiding extends MPOpleiding {

    @Schema(description = "De datum van de eerste dag waarop de opleiding wordt of is gevolgd.")
    private LocalDate datumAanvangVolgenOpleiding;

    @Schema(description = "De datum van de laatste dag dat de opleiding is gevolgd.")
    private LocalDate datumEindeVolgenOpleiding;

    @Min(0)
    @Max(3)
    @Schema(description = "Code die aangeeft in welk stadium de OPLEIDING zich bevindt.\n\n" +
            "- 0 = Onbekend\n" +
            "- 1 = Succesvol afgerond\n" +
            "- 2 = Afgebroken\n" +
            "- 3 = Lopend")
    private Integer codeStatusOpleiding;

    @Size(max = 500)
    @Schema(description = "De naam van het instituut waar de opleiding of cursus is gevolgd.")
    private String naamOpleidingsinstituut;

}
