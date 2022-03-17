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
 * AdresDetailsNederland
 */

@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({
        @JsonSubTypes.Type(Straatadres.class),
        @JsonSubTypes.Type(Postbusadres.class),
        @JsonSubTypes.Type(Antwoordnummeradres.class)
})
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance
@DiscriminatorColumn(name = "ADRES_DETAIL_TYPE")
@Schema(type = "object",
        title = "AdresDetailsNederland",
        oneOf = {Straatadres.class, Postbusadres.class, Antwoordnummeradres.class})
public abstract class AdresDetailsNederland {

    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;

}
