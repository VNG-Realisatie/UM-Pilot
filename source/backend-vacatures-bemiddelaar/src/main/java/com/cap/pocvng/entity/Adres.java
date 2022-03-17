package com.cap.pocvng.entity;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * OneOfAdresHoudingAdres
 */

@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(AdresNederland.class),
        @JsonSubTypes.Type(AdresBuitenland.class)
})

public interface Adres {


}
