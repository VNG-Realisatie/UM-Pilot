package com.cap.pocvng.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;

/**
 * StraatadresBuitenland
 */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StraatadresBuitenland implements AdresDetailsBuitenland {

    @Valid
    private StraatadresBuitenlandImpl straatadresbuitenland;

}
