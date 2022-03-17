package com.cap.pocvng.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;

/**
 * AdresBuitenland
 */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdresBuitenland implements Adres {

    @Valid
    private AdresBuitenlandImpl adresBuitenland;

}
