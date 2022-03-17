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
import javax.validation.constraints.Size;
import java.util.List;

/**
 * MPWerkzoekendeMatch
 */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MPWerkzoekendeMatch {

    @Id
    @JsonIgnore
    @GeneratedValue
    Long id;

    @Size(max = 500)
    String vumID;

    boolean fetchedDetail;

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

    @Valid
    @ElementCollection
    private List<Voorkeursland> voorkeursland;

    @Valid
    @ElementCollection
    private List<MPVervoermiddel> vervoermiddel;

    @Valid
    @Embedded
    private Mobiliteit mobiliteit;

    @Valid
    @Embedded
    private Flexibiliteit flexibiliteit;

    @Valid
    @Embedded
    private Werktijden werktijden;

    @Valid
    @ElementCollection
    private List<Contractvorm> contractvorm;

    @Valid
    @OneToOne(cascade = {CascadeType.ALL})
    private MPArbeidsmarktkwalificatie arbeidsmarktkwalificatie;

    @Valid
    @ElementCollection
    private List<SectorBeroepsEnBedrijfsleven> sector;

    @Valid
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Beroepsnaam> bemiddelingsberoep;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private AanvraagWerkzoekende aanvraagWerkzoekenden;


}
