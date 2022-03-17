package com.cap.pocvng.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

/**
 * MPAdresBuitenlandAdresBuitenland
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MPAdresBuitenlandImpl {
    @Size(max = 2)
    private String landencodeIso;

}
