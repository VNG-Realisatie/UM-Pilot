package com.cap.pocvng.dto;

import com.cap.pocvng.entity.Vacature;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Size;

/**
 * InlineResponse2001
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InlineResponse2001 {
    @Size(max = 200)
    private String bronID;

    @Valid
    private Vacature vacature;
}
