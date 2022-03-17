package com.cap.pocvng.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

/**
 * Vakvaardigheid
 */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Vakvaardigheid {

    @Size(max = 120)
    @Schema(description = "De naamgeving van een handeling die in een bepaalde mate bekwaam uitgevoerd kan worden.")
    private String omschrijving;

}
