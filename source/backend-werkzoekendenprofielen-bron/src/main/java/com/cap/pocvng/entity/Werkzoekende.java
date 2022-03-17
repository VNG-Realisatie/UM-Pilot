package com.cap.pocvng.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
@Entity
public class Werkzoekende {

    @Size(max = 200)
    @Id
    @NotNull
    private String idWerkzoekende;

    // OIN of municipality of Werkzoekende.
    @JsonIgnore
    private String oin;

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
    @ElementCollection
    @Schema(description = "Het land waarin de werkzoekende bij voorkeur wil gaan werken.")
    private List<Voorkeursland> voorkeursland;

    @Valid
    @Embedded
    @Schema(description = "De werkomstandigheden en/of de inhoud van het werk die aanwezig moeten zijn, zodat de werkzoekende het werk uit kan voeren.")
    private EisAanWerk eisAanWerk;

    @Valid
    @ElementCollection // TODO : Could be entity?
    @Schema(description = "Een object dat bedoeld is om personen of goederen te vervoeren.")
    private List<Vervoermiddel> vervoermiddel;

    @Valid
    @Embedded
    @Schema(description = "Gewenste reisafstand woon-werk.")
    private Mobiliteit mobiliteit;

    @Valid
    @Embedded
    @Schema(description = "De bereidheid van de werkzoekende om concessies te doen aan de eisen die hij stelt aan het aanvaarden van arbeid.\n\n" +
            "Norminstantie: SGR")
    private Flexibiliteit flexibiliteit;

    @Valid
    @Embedded
    @Schema(description = "De mate waarin de werkzoekende beschikbaar is voor werk.")
    private Werktijden werktijden;

    @Valid
    @ElementCollection
    @Schema(description = "De contractvorm welke van toepassing is.")
    private List<Contractvorm> contractvorm;

    @Valid
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "arbeidskwalificatie_id")
    @Schema(description = "De set van eigenschappen, kennis en kunde die een werkzoekende bezit en relevant zijn voor het vinden van werk.")
    private Arbeidsmarktkwalificatie arbeidsmarktkwalificatie;

    @Valid
    @ElementCollection //TODO: could be entity?
    @Schema(description = "De aanduiding van de unieke aansluiting van de elektronische postbus van een PERSOON of ORGANISATIE op het internet.")
    private List<Emailadres> emailadres;

    @Valid
    @ElementCollection
    @Schema(description = "De gegevens waarmee een webadres geïdentificeerd kan worden.")
    private List<Webadres> webadres;

    @Valid
    @ElementCollection
    @Schema(description = "Het telefoonnummer waaronder een PERSOON of ORGANISATIE bereikbaar is.")
    private List<Telefoonnummer> telefoonnummer;

    @Valid
    @ElementCollection
    @Schema(description = "De PERSOON of de afdeling van een ORGANISATIE waarbij een derde, voor deze in een specifieke situatie relevante, informatie kan verkrijgen.\n\n" +
            "Norminstantie: SGR")
    private List<ContactpersoonAfdeling> contactpersoon;

    @Valid
    @ElementCollection
    @Schema(description = "De sector bedroeps- en bedrijfsleven waarin de werkzoekende bemiddeld wenst te worden.")
    private List<SectorBeroepsEnBedrijfsleven> sector;

    @Valid
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Beroepsnaam> bemiddelingsberoep;


}
