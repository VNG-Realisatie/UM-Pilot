package com.cap.pocvng.entity;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * MPOpleidingsnaam
 */


@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(OpleidingsnaamGecodeerd.class),
        @JsonSubTypes.Type(MPOpleidingsnaamOngecodeerd.class)
})
public interface MPOpleidingsnaam {

}
