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
 * OneOfAdresHoudingAdres
 */

@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({
        @JsonSubTypes.Type(AdresNederland.class),
        @JsonSubTypes.Type(AdresBuitenland.class)
})
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance
@DiscriminatorColumn(name = "ADRES_TYPE")
@Schema(type = "object",
        title = "Adres",
        oneOf = {AdresNederland.class, AdresBuitenland.class})
public abstract class Adres {
    
    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;

}
