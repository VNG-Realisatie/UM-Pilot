package com.cap.pocvng.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

/**
 * MPVacature
 */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MPVacature {

    @Size(max = 1) //TODO: check if correct
    private String codeWerkEnDenkniveauMinimaal;

    @Min(1)
    @Max(2)
    private Integer indicatieLdrRegistratie;

    private LocalDate sluitingsDatumVacature;

    @Valid
    private List<MPSollicitatiewijze> sollicitatiewijze;

    @Valid
    private MPWerkgever werkgever;

    @Valid
    private SectorBeroepsEnBedrijfsleven sector;

    @Valid
    private MPArbeidsVoorwaarden arbeidsVoorwaarden;

    @Valid
    private List<Contractvorm> contractvorm;

    @Valid
    private Beroepsnaam beroep;

    @Valid
    private List<Werkervaring> werkervaring;

    @Valid
    private List<Rijbewijs> rijbewijs;

    @Valid
    private List<MPVervoermiddel> vervoermiddel;

    @Valid
    private Flexibiliteit flexibiliteit;

    @Valid
    private Werktijden werktijden;

    @Valid
    private List<Cursus> cursus;

    @Valid
    private List<MPOpleiding> opleiding;

    @Valid
    private List<Gedragscompetentie> gedragscompetentie;

    @Valid
    private List<Vakvaardigheid> vakvaardigheid;

    @Valid
    private List<Taalbeheersing> taalbeheersing;

}
