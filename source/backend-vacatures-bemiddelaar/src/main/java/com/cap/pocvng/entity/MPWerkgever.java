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
import java.util.List;

/**
 * MPWerkgever
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Schema(description = "De natuurlijke of RECHTSPERSOON die, of het samenwerkingsverband dat, één of meer PERSONEN arbeid laat verrichten.")
public class MPWerkgever {

    @JsonIgnore
    @Id
    @GeneratedValue
    private Long id;

    @Valid
    @ElementCollection
    private List<MPAdresHouding> adresHouding;

    @Valid
    @ElementCollection
    private List<SectorBeroepsEnBedrijfsleven> sector;


}
