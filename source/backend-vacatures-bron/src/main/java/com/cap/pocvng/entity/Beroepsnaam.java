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

/**
 * Beroepsnaam
 */


@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({
        @JsonSubTypes.Type(BeroepsnaamGecodeerd.class),
        @JsonSubTypes.Type(BeroepsnaamOngecodeerd.class)
})
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance
@DiscriminatorColumn(name = "BEROEP_TYPE")
@Schema(type = "object",
        title = "Beroepsnaam",
        oneOf = {BeroepsnaamGecodeerd.class, BeroepsnaamOngecodeerd.class})
public abstract class Beroepsnaam {

    @JsonIgnore
    @Id
    @GeneratedValue
    private Long id;
}
