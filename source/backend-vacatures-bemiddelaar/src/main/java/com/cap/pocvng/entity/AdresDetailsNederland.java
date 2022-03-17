package com.cap.pocvng.entity;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * AdresDetailsNederland
 */

@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(Straatadres.class),
        @JsonSubTypes.Type(Postbusadres.class),
        @JsonSubTypes.Type(Antwoordnummeradres.class)
})

public interface AdresDetailsNederland {

}
