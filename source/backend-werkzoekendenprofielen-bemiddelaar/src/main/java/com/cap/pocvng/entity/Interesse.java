package com.cap.pocvng.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

/**
 * Interesse
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Schema(description = "Een onderwerp waar de werkzoekende belangstelling voor heeft.")
public class Interesse {

    @Size(max = 120)
    @Schema(description = "De naam van een interesse zoals deze is opgegeven door werkzoekende of werkgever.")
    private String naamInteresse;

}
