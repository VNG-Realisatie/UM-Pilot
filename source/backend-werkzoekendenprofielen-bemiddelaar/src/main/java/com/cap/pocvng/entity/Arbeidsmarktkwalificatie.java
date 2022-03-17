package com.cap.pocvng.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

/**
 * Arbeidsmarktkwalificatie
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "De set van eigenschappen, kennis en kunde die een werkzoekende bezit en relevant zijn voor het vinden van werk.")
public class Arbeidsmarktkwalificatie {


    @Min(0)
    @Max(7)
    @Schema(description = "Code die het opleidingsniveau aangeeft dat bereikt is op basis van opleiding en/of werkervaring.\n\n" +
            "- 0 = niet van toepassing\n" +
            "- 1 = geen basisopleiding\n" +
            "- 2 = basisniveau\n" +
            "- 3 = vmbo-niveau\n" +
            "- 4 = mbo/havo/vwo-niveau\n" +
            "- 5 = hbo-niveau\n" +
            "- 6 = wetenschappelijk onderwijs\n" +
            "- 7 = (nog) niet bekend")
    private Integer codeWerkEnDenkniveauWerkzoekende;

    @Valid
    private List<Vakvaardigheid> vakvaardigheid;

    @Valid
    private List<Taalbeheersing> taalbeheersing;

    @Valid
    private List<Gedragscompetentie> gedragscompetentie;

    @Valid
    private List<Interesse> interesse;

    @Valid
    private List<Rijbewijs> rijbewijs;

    @Valid
    private List<Cursus> cursus;

    @Valid
    private List<Opleiding> opleiding;

    @Valid
    private List<Werkervaring> werkervaring;


}
