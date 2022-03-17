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

/**
 * Webadres
 */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Schema(description = "De gegevens waarmee een webadres geïdentificeerd kan worden.")
public class Webadres {
    @OneOfInt({3,4})
    @Schema(description = "De code die het soort webadres aangeeft.\n\n" +
            "- 3 = Webadres werkgever\n" +
            "- 4 = Webadres online sollicitatieformulier")
    private Integer codeWebadres;

    @Pattern(regexp = "^((?:https?:\\/\\/)?[^./]+(?:\\.[^./]+)+(?:\\/.*)?)$")
    @Schema(description = "De Uniform Resource Locator (URL) van het (web)adres van een bestand. ")
    private String url;

}
