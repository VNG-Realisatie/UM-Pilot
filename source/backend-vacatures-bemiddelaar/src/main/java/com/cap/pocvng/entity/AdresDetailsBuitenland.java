package com.cap.pocvng.entity;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * AdresDetailsBuitenland
 */

@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(StraatadresBuitenland.class),
        @JsonSubTypes.Type(PostbusadresBuitenland.class)
})

public interface AdresDetailsBuitenland {

}
