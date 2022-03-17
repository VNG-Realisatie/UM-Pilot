package com.cap.pocvng.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * PostbusadresPostbusadres
 */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostbusadresImpl {
    @Min(0)
    @Max(99999)
    private Integer postbusnummer;

}
