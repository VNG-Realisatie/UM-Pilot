package com.cap.pocvng.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

/**
 * InlineResponse200
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InlineResponse200 {
    @Size(max = 100)
    private String vraagID;
}
