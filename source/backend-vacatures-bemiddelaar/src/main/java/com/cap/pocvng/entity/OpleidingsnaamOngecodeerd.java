package com.cap.pocvng.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

/**
 * OpleidingsnaamOngecodeerd
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OpleidingsnaamOngecodeerd implements Opleidingsnaam {

    @Size(max = 120)
    private String naamOpleidingOngecodeerd;

    @Size(max = 2000)
    private String omschrijvingOpleiding;
}
