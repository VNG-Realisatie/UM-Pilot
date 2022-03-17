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
 * AdresDetailsBuitenland
 */

@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({
        @JsonSubTypes.Type(StraatadresBuitenland.class),
        @JsonSubTypes.Type(PostbusadresBuitenland.class)
})
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance
@DiscriminatorColumn(name = "ADRES_DETAIL_TYPE")
@Schema(type = "object",
        title = "AdresDetailsBuitenland",
        oneOf = {StraatadresBuitenland.class, PostbusadresBuitenland.class})
public abstract class AdresDetailsBuitenland {

    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;

}
