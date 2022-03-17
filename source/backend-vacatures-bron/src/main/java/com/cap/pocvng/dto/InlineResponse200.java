package com.cap.pocvng.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * InlineResponse200
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InlineResponse200 {
    @NotNull
    private Boolean maximumAantalResultatenBereikt;

    @NotNull
    private VacatureMatchingProfielen matches;

}
