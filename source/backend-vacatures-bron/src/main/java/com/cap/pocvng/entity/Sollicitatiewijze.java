package com.cap.pocvng.entity;

import com.cap.pocvng.validator.OneOfString;
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
 * Sollicitatiewijze
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Sollicitatiewijze {
    @Size(max = 1)
    @OneOfString({"1", "2", "3", "4"})
    @Schema(description = "De code die aangeeft op welke wijze de sollicitaties ontvangen kunnen worden.\n\n" +
            "1 = Per post\n" +
            "2 = Per telefoon\n" +
            "3 = Per e-mail\n" +
            "4 = Per online sollicitatieformulier",
            allowableValues = {"1", "2", "3", "4"})
    private String codeSollicitatiewijze;

    @Valid
    @Embedded
    private Webadres webadres;

}
