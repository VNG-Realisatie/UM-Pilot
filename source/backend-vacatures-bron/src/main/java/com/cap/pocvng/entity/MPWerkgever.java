package com.cap.pocvng.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import java.util.List;

/**
 * MPWerkgever
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MPWerkgever {

    @Valid
    private List<MPAdresHouding> adresHouding;

    @Valid
    private List<SectorBeroepsEnBedrijfsleven> sector;


}
