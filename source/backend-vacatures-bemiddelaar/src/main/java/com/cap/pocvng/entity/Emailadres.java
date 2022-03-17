package com.cap.pocvng.entity;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

/**
 * Emailadres
 */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Emailadres {

    @Size(max = 320)
    @JsonValue
    private String emailadres;

}
