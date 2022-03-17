package com.cap.pocvng.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Size;

/**
 * MPAdresHouding
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MPAdresHouding {
    @Size(max = 1)
    private String codeFunctieAdres;

    @Valid
    private MPAdresBuitenland adres;

}
