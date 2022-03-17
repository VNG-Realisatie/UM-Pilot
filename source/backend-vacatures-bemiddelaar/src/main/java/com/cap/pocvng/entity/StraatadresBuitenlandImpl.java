package com.cap.pocvng.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

/**
 * StraatadresBuitenlandStraatadresbuitenland
 */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StraatadresBuitenlandImpl {
    @Size(max = 9)
    private String huisnummerBuitenland;

    @Size(max = 24)
    private String straatnaamBuitenland;

}
