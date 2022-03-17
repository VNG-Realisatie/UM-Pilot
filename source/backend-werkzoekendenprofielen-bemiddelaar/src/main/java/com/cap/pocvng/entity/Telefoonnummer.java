package com.cap.pocvng.entity;

import com.cap.pocvng.serializer.ValueSerializerTelefoonnummer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.Pattern;

/**
 * Telefoonummer
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@JsonSerialize(using = ValueSerializerTelefoonnummer.class)
@Schema(description = "Het telefoonnummer waaronder een PERSOON of ORGANISATIE bereikbaar is.")

public class Telefoonnummer {

    @Pattern(regexp = "^(0|(\\+|00)31)(([1-9]([0-9]){8})|(6-?[1-9]([0-9]){7})|([1-57-9]\\d-?[1-9]([0-9]){6})|([1-57-9]\\d{2}-?[1-9]([0-9]){5}))$")
    @Schema(type = "String",
            description = "Het telefoonnummer waaronder een PERSOON of ORGANISATIE bereikbaar is.",
            pattern = "^(0|(\\+|00)31)(([1-9]([0-9]){8})|(6-?[1-9]([0-9]){7})|([1-57-9]\\d-?[1-9]([0-9]){6})|([1-57-9]\\d{2}-?[1-9]([0-9]){5}))$")
    private String telefoonnummer;

}


//