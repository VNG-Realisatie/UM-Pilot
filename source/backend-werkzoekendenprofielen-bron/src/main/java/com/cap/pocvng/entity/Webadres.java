package com.cap.pocvng.entity;

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
public class Webadres {

    @Min(1)
    @Max(2)
    @Schema(description = "De code die het soort webadres aangeeft.\n\n" +
            "- 1 = Webadres werkzoekende profiel (webadres waarop een profielbeschrijving van de werkzoekende is te vinden)\n" +
            "- 2 = Webadres CV (webadres waarop het CV van  een werkzoekende te vinden is)")
    private Integer codeWebadres;

    @Pattern(regexp = "^((?:https?:\\/\\/)?[^./]+(?:\\.[^./]+)+(?:\\/.*)?)$")
    @Schema(description = "De Uniform Resource Locator (URL) van het (web)adres van een bestand. ")
    private String url;
}
