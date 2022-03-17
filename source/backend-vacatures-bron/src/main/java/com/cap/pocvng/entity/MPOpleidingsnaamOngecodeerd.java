package com.cap.pocvng.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

/**
 * MPOpleidingsnaamOngecodeerd
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MPOpleidingsnaamOngecodeerd implements MPOpleidingsnaam {
    @Size(max = 120)
    private String naamOpleidingOngecodeerd;
}
