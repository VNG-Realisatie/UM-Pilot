package com.cap.pocvng.dto;

import com.cap.pocvng.entity.MPWerkzoekendeMatch;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * WerkzoekendeMatchingProfielen for returning matching profiles.
 */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WerkzoekendeMatchingProfielen {

    @JsonProperty("matches")
    @NotNull
    private List<MPWerkzoekendeMatch> mpWerkzoekendeMatches;

}
