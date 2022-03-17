package com.cap.pocvng.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
public class MPArbeidsmarktkwalificatie {

    @Min(0)
    @Max(7)
    private Integer codeWerkEnDenkniveauWerkzoekende;

    @Valid
    private List<Vakvaardigheid> vakvaardigheid;

    @Valid
    private List<Taalbeheersing> taalbeheersing;

    @Valid
    private List<Gedragscompetentie> gedragscompetentie;

    @Valid
    private List<Rijbewijs> rijbewijs;

    @Valid
    private List<MPCursus> cursus;

    @Valid
    private List<MPOpleiding> opleiding;

    @Valid
    private List<MPWerkervaring> werkervaring;

}
