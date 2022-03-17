package com.cap.pocvng.entity;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.persistence.Inheritance;

import static javax.persistence.InheritanceType.SINGLE_TABLE;

/**
 * Opleidingsnaam
 */

@Inheritance(strategy = SINGLE_TABLE)
@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(OpleidingsnaamGecodeerd.class),
        @JsonSubTypes.Type(OpleidingsnaamOngecodeerd.class)
})

public interface Opleidingsnaam {

}
