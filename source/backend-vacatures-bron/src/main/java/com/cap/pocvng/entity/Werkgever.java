package com.cap.pocvng.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Werkgever
 */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Werkgever {

    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;

    @Size(max = 500)
    @Schema(description = "De naam waaronder een ORGANISATIE aan het maatschappelijk verkeer deelneemt.")
    private String handelsnaamOrganisatie;

    @Valid
    @ElementCollection
    private List<Webadres> webadres;

    @Valid
    @ElementCollection
    private List<AdresHouding> adresHouding;

    @Valid
    @OneToMany(cascade = {CascadeType.ALL})
    private List<ContactpersoonAfdeling> contactpersoon;

    @Valid
    @ElementCollection
    private List<SectorBeroepsEnBedrijfsleven> sector;

}
