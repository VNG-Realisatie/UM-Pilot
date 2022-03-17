package com.cap.pocvng.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.validation.constraints.Size;

/**
 * MPOpleidingsnaamOngecodeerd
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MPOpleidingsnaamOngecodeerd extends MPOpleidingsnaam {
    @Size(max = 120)
    private String naamOpleidingOngecodeerd;
}
