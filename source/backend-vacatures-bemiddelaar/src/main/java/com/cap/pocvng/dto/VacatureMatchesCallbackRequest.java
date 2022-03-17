package com.cap.pocvng.dto;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VacatureMatchesCallbackRequest {

    @NotBlank
    @Size(max = 100)
    private String vraagID;

    @NotNull
    private Boolean maximumAantalResultatenBereikt;

    @Valid
    @JsonUnwrapped
    private VacatureMatchingProfielen matches;
}
