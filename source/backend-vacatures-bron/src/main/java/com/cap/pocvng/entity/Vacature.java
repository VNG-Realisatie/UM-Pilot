package com.cap.pocvng.entity;

import com.cap.pocvng.validator.OneOfString;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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
@Entity
public class Vacature {

    @Size(max = 200)
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String idVacature;

    // OIN of municipality of vacature
    @JsonIgnore
    private String oin;

    @Size(max = 120)
    @Schema(description = "De naam van de vacature.")
    private String naamVacature;

    @Size(max = 11)
    @Schema(description = "Het unieke nummer van de vacature, dat automatisch wordt toegekend.")
    private String nummerVacature;

    @Size(max = 2000)
    @Schema(description = "De omschrijving die de achtergrondinformatie van de vacature weergeeft.")
    private String omschrijvingVacature;

    @Size(max = 1) //TODO: check if correct
    @OneOfString({"0", "1", "2", "3", "4", "5", "6", "7"})
    @Schema(description = "Code die het minimum opleidingsniveau aangeeft dat bereikt is op basis van opleiding en/of werkervaring.\n\n" +
            "- 0 = niet van toepassing\n" +
            "- 1 = geen basisopleiding\n" +
            "- 2 = basisniveau\n" +
            "- 3 = vmbo-niveau\n" +
            "- 4 = mbo/havo/vwo-niveau\n" +
            "- 5 = hbo-niveau\n" +
            "- 6 = wetenschappelijk onderwijs\n" +
            "- 7 = (nog) niet bekend",
            allowableValues = {"0", "1", "2", "3", "4", "5", "6", "7"})
    private String codeWerkEnDenkniveauMinimaal;

    @Min(1)
    @Max(2)
    @Schema(description = "De indicatie die aangeeft of voor de natuurlijk persoon in het Landelijk Doelgroepregister een grondslag met betrekking tot arbeidsbeperktheid geregistreerd is.\n\n" +
            "- 1 = Ja\n" +
            "- 2 = Nee")
    private Integer indicatieLdrRegistratie;

    @Schema(description = "De laatste dag dat een vacature nog open staat.")
    private LocalDate sluitingsDatumVacature;

    @Valid
    @ElementCollection
    @Schema(description = "De wijze waarop de werkgever de sollicitaties op de vacature wil ontvangen.")
    private List<Sollicitatiewijze> sollicitatiewijze;

    @Valid
    @OneToOne(cascade = {CascadeType.ALL})
    @Schema(description = "De natuurlijke of RECHTSPERSOON die, of het samenwerkingsverband dat, één of meer PERSONEN arbeid laat verrichten.")
    private Werkgever werkgever;

    @Valid
    @Embedded
    @Schema(description = "De sector bedroeps- en bedrijfsleven waarin de werkzoekende bemiddeld wenst te worden.")
    private SectorBeroepsEnBedrijfsleven sector;

    @Valid
    @Embedded
    @Schema(description = "De voorwaarden waaronder werk wordt verricht.")
    private ArbeidsVoorwaarden arbeidsVoorwaarden;

    @Valid
    @ElementCollection
    @Schema(description = "De contractvorm welke van toepassing is.")
    private List<Contractvorm> contractvorm;

    @Valid
    @OneToOne(cascade = {CascadeType.ALL})
    private Beroepsnaam beroep;

    @Valid
    @ElementCollection
    @Schema(description = "De werkzaamheden die een natuurlijk persoon in een bepaalde periode als beroep heeft uitgevoerd.")
    private List<Werkervaring> werkervaring;

    @Valid
    @ElementCollection
    @Schema(description = "Geautoriseerd document om een motorvoertuig over de weg te mogen besturen.\n\n" +
            "Norminstantie: SGR")
    private List<Rijbewijs> rijbewijs;

    @Valid
    @ElementCollection
    @Schema(description = "Een object dat bedoeld is om personen of goederen te vervoeren.")
    private List<Vervoermiddel> vervoermiddel;

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
    @Schema(description = "De cursus die de werkzoekende volgt, heeft gevolgd of heeft ingepland om te gaan volgen.")
    private List<Cursus> cursus;

    @Valid
    @ElementCollection
    @Schema(description = "De scholing die de werkzoekende volgt of heeft gevolgd.")
    private List<Opleiding> opleiding;

    @Valid
    @ElementCollection
    @Schema(description = "De karaktereigenschappen van een natuurlijk persoon.")
    private List<Gedragscompetentie> gedragscompetentie;

    @Valid
    @ElementCollection
    @Schema(description = "Het vermogen om een handeling in een bepaalde mate bekwaam uit te voeren.")
    private List<Vakvaardigheid> vakvaardigheid;

    @Valid
    @ElementCollection
    @Schema(description = "De mate waarin een natuurlijk persoon een taal beheerst.")
    private List<Taalbeheersing> taalbeheersing;

}
