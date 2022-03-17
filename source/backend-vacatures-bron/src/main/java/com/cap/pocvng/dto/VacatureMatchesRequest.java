package com.cap.pocvng.dto;

import com.cap.pocvng.entity.MPVacature;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


/**
 * VacatureMatchesRequest for requesting matching vacatures.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VacatureMatchesRequest {

    @NotNull
    @Valid MPVacature vraagObject;
}
