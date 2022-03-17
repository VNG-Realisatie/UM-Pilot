package com.cap.pocvng.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Voorkeursland
 */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Voorkeursland {

    @Size(max = 2)
    @Pattern(regexp = "^[A-Z]{2}$")
    @Schema(description = "De code van een huidig land of gebiedsdeel conform ISO 3166.\n\n" +
            "Norminstantie: ISO 3166 (Entity, Alpha-2 code)")
    private String landencodeIso;


}
