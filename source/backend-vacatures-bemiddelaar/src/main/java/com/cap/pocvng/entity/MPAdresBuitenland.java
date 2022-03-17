package com.cap.pocvng.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.validation.Valid;

/**
 * MPAdresBuitenland
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class MPAdresBuitenland {
    @Valid
    @Embedded
    private MPAdresBuitenlandImpl adresBuitenland;

}
