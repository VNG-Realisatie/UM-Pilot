package com.cap.pocvng.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * MPOpleidingsnaam
 */


@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(OpleidingsnaamGecodeerd.class),
        @JsonSubTypes.Type(MPOpleidingsnaamOngecodeerd.class)
})

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance
@DiscriminatorColumn(name = "OPLEIDING_TYPE")
public abstract class MPOpleidingsnaam {

    @JsonIgnore
    @Id
    @GeneratedValue
    private Long id;
}
