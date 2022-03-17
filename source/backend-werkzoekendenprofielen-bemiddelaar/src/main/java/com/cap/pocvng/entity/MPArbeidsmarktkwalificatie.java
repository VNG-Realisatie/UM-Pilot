package com.cap.pocvng.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

/**
 * MPArbeidsmarktkwalificatie
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Schema(description = "De set van eigenschappen, kennis en kunde die een werkzoekende bezit en relevant zijn voor het vinden van werk.")
public class MPArbeidsmarktkwalificatie {

    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;

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
    @ElementCollection
    private List<Vakvaardigheid> vakvaardigheid;

    @Valid
    @ElementCollection
    private List<Taalbeheersing> taalbeheersing;

    @Valid
    @ElementCollection
    private List<Gedragscompetentie> gedragscompetentie;

    @Valid
    @ElementCollection
    private List<Rijbewijs> rijbewijs;

    @Valid
    @ElementCollection
    private List<MPCursus> cursus;

    @Valid
    @ElementCollection
    private List<MPOpleiding> opleiding;

    @Valid
    @ElementCollection
    private List<MPWerkervaring> werkervaring;

}
