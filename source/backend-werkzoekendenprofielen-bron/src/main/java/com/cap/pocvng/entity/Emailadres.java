package com.cap.pocvng.entity;

import com.cap.pocvng.serializer.ValueSerializerEmail;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Emailadres
 */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@JsonSerialize(using = ValueSerializerEmail.class)
@Schema(type = "string",
        description = "Het adres waaronder een PERSOON of ORGANISATIE per elektronische post bereikbaar is.",
        pattern = "^\\S+@\\S+\\.\\S+$")
public class Emailadres {

    @Size(max = 320)
    @Pattern(regexp = "^\\S+@\\S+\\.\\S+$")
    private String emailadres;
}
