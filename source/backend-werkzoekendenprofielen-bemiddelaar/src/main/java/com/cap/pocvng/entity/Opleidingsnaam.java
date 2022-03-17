package com.cap.pocvng.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.InheritanceType.SINGLE_TABLE;

/**
 * Opleidingsnaam
 */

@Inheritance(strategy = SINGLE_TABLE)
@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION, include = JsonTypeInfo.As.EXISTING_PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(OpleidingsnaamGecodeerd.class),
        @JsonSubTypes.Type(OpleidingsnaamOngecodeerd.class)
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorColumn(name = "OPLEIDING_TYPE")
@Schema(type = "object",
        title = "Opleidingsnaam",
        oneOf = {OpleidingsnaamGecodeerd.class, OpleidingsnaamOngecodeerd.class})
public abstract class Opleidingsnaam {

    @GeneratedValue
    @Id
    @JsonIgnore
    private Long id;

}
