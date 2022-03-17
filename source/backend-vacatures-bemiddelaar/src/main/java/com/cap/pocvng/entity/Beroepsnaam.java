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
 * Beroepsnaam
 */


@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(BeroepsnaamGecodeerd.class),
        @JsonSubTypes.Type(BeroepsnaamOngecodeerd.class)
})


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance
@DiscriminatorColumn(name = "BEROEP_TYPE")
public abstract class Beroepsnaam {

    @JsonIgnore
    @Id
    @GeneratedValue
    private Long id;
}
