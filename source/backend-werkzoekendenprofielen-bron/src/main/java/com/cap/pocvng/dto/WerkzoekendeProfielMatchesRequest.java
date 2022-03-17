package com.cap.pocvng.dto;

import com.cap.pocvng.entity.MPWerkzoekende;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * WerkzoekendeProfielMatchesRequest for requesting matching profiles.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WerkzoekendeProfielMatchesRequest {

    @NotNull
    @Valid
    MPWerkzoekende vraagObject;

}
