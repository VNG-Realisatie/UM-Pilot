package com.cap.pocvng.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Size;

/**
 * Sollicitatiewijze
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sollicitatiewijze {
    @Size(max = 1)
    private String codeSollicitatiewijze;

    @Valid
    private Webadres webadres;

}
