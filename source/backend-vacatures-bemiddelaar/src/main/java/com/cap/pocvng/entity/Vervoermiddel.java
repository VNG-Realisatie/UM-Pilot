package com.cap.pocvng.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Vervoermiddel
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vervoermiddel extends MPVervoermiddel {

    @Min(0)
    @Max(99)
    private Integer codeVervoermiddel;

}
