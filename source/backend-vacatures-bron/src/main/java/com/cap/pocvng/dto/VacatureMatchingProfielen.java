package com.cap.pocvng.dto;

import com.cap.pocvng.entity.MPVacatureMatch;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * VacatureMatchingProfielen
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VacatureMatchingProfielen {

    @JsonValue
    private List<MPVacatureMatch> matches;
}
