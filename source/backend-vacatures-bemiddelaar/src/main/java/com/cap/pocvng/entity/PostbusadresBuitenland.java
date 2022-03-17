package com.cap.pocvng.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;

/**
 * PostbusadresBuitenland
 */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostbusadresBuitenland implements AdresDetailsBuitenland {

    @Valid
    private PostbusadresBuitenlandImpl postbusadresbuitenland;

}
