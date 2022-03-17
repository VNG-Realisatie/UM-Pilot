package com.cap.pocvng.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.validation.Valid;

/**
 * PostbusadresBuitenland
 */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PostbusadresBuitenland extends AdresDetailsBuitenland {

    @Valid
    @Embedded
    private PostbusadresBuitenlandImpl postbusadresbuitenland;

}
