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
 * Vacature
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vacature {

    @Size(max = 200)
    private String idVacature;

    @Size(max = 120)
    private String naamVacature;

    @Size(max = 11)
    private String nummerVacature;

    @Size(max = 2000)
    private String omschrijvingVacature;

    @Size(max = 1) //TODO: check if correct
    private String codeWerkEnDenkniveauMinimaal;

    @Min(0)
    @Max(2)
    private Integer indicatieLdrRegistratie;

    private LocalDate sluitingsDatumVacature;

    @Valid
    private List<Sollicitatiewijze> sollicitatiewijze;

    @Valid
    private Werkgever werkgever;

    @Valid
    private SectorBeroepsEnBedrijfsleven sector;

    @Valid
    private ArbeidsVoorwaarden arbeidsVoorwaarden;

    @Valid
    private List<Contractvorm> contractvorm;

    @Valid
    private Beroepsnaam beroep;

    @Valid
    private List<Werkervaring> werkervaring;

    @Valid
    private List<Rijbewijs> rijbewijs;

    @Valid
    private List<Vervoermiddel> vervoermiddel;

    @Valid
    private Flexibiliteit flexibiliteit;

    @Valid
    private Werktijden werktijden;

    @Valid
    private List<Cursus> cursus;

    @Valid
    private List<Opleiding> opleiding;

    @Valid
    private List<Gedragscompetentie> gedragscompetentie;

    @Valid
    private List<Vakvaardigheid> vakvaardigheid;

    @Valid
    private List<Taalbeheersing> taalbeheersing;

}
