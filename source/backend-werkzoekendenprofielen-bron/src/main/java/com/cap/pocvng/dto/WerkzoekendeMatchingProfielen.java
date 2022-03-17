package com.cap.pocvng.dto;

import com.cap.pocvng.entity.MPWerkzoekendeMatch;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * WerkzoekendeMatchingProfielen for returning matching profiles.
 */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WerkzoekendeMatchingProfielen {

    @JsonValue
    private List<MPWerkzoekendeMatch> matches;

}
