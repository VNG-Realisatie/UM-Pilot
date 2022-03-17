package com.cap.pocvng.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

/**
 * Cursus
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Cursus {

    @Size(max = 120)
    @Schema(description = "De naam van de cursus zoals deze bekend staat.")
    private String naamCursus;

}
