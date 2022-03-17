package com.cap.pocvng.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * Cursus
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "De cursus die de werkzoekende volgt, heeft gevolgd of heeft ingepland om te gaan volgen.")
public class Cursus extends MPCursus {

    @Schema(description = "De datum van de eerste dag waarop de cursus wordt of is gevolgd.")
    private LocalDate datumAanvangVolgenCursus;

    @Schema(description = "De datum van de laatste dag dat de cursus is gevolgd.")
    private LocalDate datumEindeVolgenCursus;

    @Size(max = 500)
    @Schema(description = "De naam van het instituut waar de opleiding of cursus is gevolgd.")
    private String naamOpleidingsinstituut;

}
