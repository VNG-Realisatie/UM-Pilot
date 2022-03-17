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

@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({
        @JsonSubTypes.Type(OpleidingsnaamGecodeerd.class),
        @JsonSubTypes.Type(OpleidingsnaamOngecodeerd.class)
})

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = SINGLE_TABLE)
@DiscriminatorColumn(name = "OPLEIDING_TYPE")
@Schema(type = "object",
        title = "Opleidingsnaam",
        oneOf = {OpleidingsnaamGecodeerd.class, OpleidingsnaamOngecodeerd.class})
public abstract class Opleidingsnaam {

    @JsonIgnore
    @Id
    @GeneratedValue
    private Long id;

}
