package com.cap.pocvng.entity;

import com.cap.pocvng.validator.OneOfString;
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
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

/**
 * MPVacatureMatch
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MPVacatureMatch {

    @Id
    @JsonIgnore
    @GeneratedValue
    Long id;

    @Size(max = 500)
    String vumID;

    boolean fetchedDetail;

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
    private List<MPSollicitatiewijze> sollicitatiewijze;

    @Valid
    @OneToOne(cascade = {CascadeType.ALL})
    private MPWerkgever werkgever;

    @Valid
    @Embedded
    private SectorBeroepsEnBedrijfsleven sector;

    @Valid
    @Embedded
    private MPArbeidsVoorwaarden arbeidsVoorwaarden;

    @Valid
    @ElementCollection
    private List<Contractvorm> contractvorm;

    @Valid
    @OneToOne(cascade = {CascadeType.ALL})
    private Beroepsnaam beroep;

    @Valid
    @ElementCollection
    private List<Werkervaring> werkervaring;

    @Valid
    @ElementCollection
    private List<Rijbewijs> rijbewijs;

    @Valid
    @ElementCollection
    private List<MPVervoermiddel> vervoermiddel;

    @Valid
    @Embedded
    private Flexibiliteit flexibiliteit;

    @Valid
    @Embedded
    private Werktijden werktijden;

    @Valid
    @ElementCollection
    private List<Cursus> cursus;

    @Valid
    @ElementCollection
    private List<MPOpleiding> opleiding;

    @Valid
    @ElementCollection
    private List<Gedragscompetentie> gedragscompetentie;

    @Valid
    @ElementCollection
    private List<Vakvaardigheid> vakvaardigheid;

    @Valid
    @ElementCollection
    private List<Taalbeheersing> taalbeheersing;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private AanvraagVacature aanvraagVacatures;
}
