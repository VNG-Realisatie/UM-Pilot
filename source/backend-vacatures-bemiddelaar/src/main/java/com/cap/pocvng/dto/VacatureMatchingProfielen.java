package com.cap.pocvng.dto;

import com.cap.pocvng.entity.MPVacatureMatch;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * VacatureMatchingProfielen
 */

@Getter
@Setter
@NoArgsConstructor

public class VacatureMatchingProfielen {

    @JsonProperty("matches")
    @NotNull
    private List<MPVacatureMatch> mpVacatureMatches;

}
