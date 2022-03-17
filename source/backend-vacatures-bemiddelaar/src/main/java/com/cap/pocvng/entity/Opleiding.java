package com.cap.pocvng.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Opleiding
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Opleiding {
    @Min(0)
    @Max(9)
    private Integer codeNiveauOpleiding;

    @Min(0)
    @Max(1)
    private Integer indicatieDiploma;

    @Valid
    private Opleidingsnaam opleidingsnaam;
}
