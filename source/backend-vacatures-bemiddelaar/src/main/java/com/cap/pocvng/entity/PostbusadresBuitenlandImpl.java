package com.cap.pocvng.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

/**
 * PostbusadresBuitenlandPostbusadresbuitenland
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostbusadresBuitenlandImpl {
    @Size(max = 9)
    private String postbusnummerBuitenland;
}
