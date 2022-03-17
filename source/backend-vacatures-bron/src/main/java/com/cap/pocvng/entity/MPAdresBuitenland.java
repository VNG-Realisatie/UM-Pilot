package com.cap.pocvng.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;

/**
 * MPAdresBuitenland
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MPAdresBuitenland {
    @Valid
    private MPAdresBuitenlandImpl adresBuitenland;

}
