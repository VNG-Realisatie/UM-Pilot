package com.cap.pocvng.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

/**
 * Webadres
 */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Webadres {
    @Min(0)
    @Max(99)
    private Integer codeWebadres;

    @Pattern(regexp = "^((?:https?:\\/\\/)?[^./]+(?:\\.[^./]+)+(?:\\/.*)?)$")
    private String url;

}
