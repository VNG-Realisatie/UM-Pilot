package com.cap.pocvng.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * StraatadresStraatadres
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class StraatadresImpl {
    @Size(max = 2)
    private String aanduidingBijHuisnummer;

    @Size(max = 1)
    private String huisletter;

    @Min(0)
    @Max(99999)
    private Integer huisnummer;

    @Size(max = 6)
    private String huisnummertoevoeging;

    @Size(max = 80)
    private String naamOpenbareRuimte;

    @Size(max = 24)
    private String straatnaam;

    @Size(max = 2)
    private String woonbootverwijzing;

    @Size(max = 2)
    private String woonwagenverwijzing;

}
