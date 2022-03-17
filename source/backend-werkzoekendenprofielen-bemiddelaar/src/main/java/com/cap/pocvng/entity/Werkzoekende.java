package com.cap.pocvng.entity;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Werkzoekende
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Werkzoekende {

    @Size(max = 200)
    @NotNull
    private String idWerkzoekende;

    @Min(1)
    @Max(2)
    @Schema(description = "De indicatie die aangeeft of voor de natuurlijk persoon in het Landelijk Doelgroepregister een grondslag met betrekking tot arbeidsbeperktheid geregistreerd is.\n\n" +
            "- 1 = Ja\n" +
            "- 2 = Nee")
    private Integer indicatieLdrRegistratie;

    @Min(1)
    @Max(2)
    @Schema(description = "Indicatie die aangeeft of er contactgegevens, van de werkzoekende en/of de contactpersoon, in het profiel aanwezig zijn.\n\n" +
            "- 1 = Ja\n" +
            "- 2 = Nee")
    private Integer indicatieBeschikbaarheidContactgegevens;

    @Size(max = 2000)
    @Schema(description = "De persoonlijke presentatie van de werkzoekende.")
    private String persoonlijkePresentatie;

    @Valid
    private List<Voorkeursland> voorkeursland;

    @Valid
    private EisAanWerk eisAanWerk;

    @Valid
    private List<Vervoermiddel> vervoermiddel;

    @Valid
    private Mobiliteit mobiliteit;

    @Valid
    private Flexibiliteit flexibiliteit;

    @Valid
    private Werktijden werktijden;

    @Valid
    private List<Contractvorm> contractvorm;

    @Valid
    private Arbeidsmarktkwalificatie arbeidsmarktkwalificatie;

    @Valid
    private List<Emailadres> emailadres;

    @Valid
    private List<Webadres> webadres;

    @Valid
    private List<Telefoonnummer> telefoonnummer;

    @Valid
    private List<ContactpersoonAfdeling> contactpersoon;

    @Valid
    private List<SectorBeroepsEnBedrijfsleven> sector;

    @Valid
    private List<Beroepsnaam> bemiddelingsberoep;


}
