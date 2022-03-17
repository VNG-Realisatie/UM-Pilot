package com.cap.pocvng.entity;

import com.cap.pocvng.validator.OneOfInt;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Vervoermiddel
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Vervoermiddel extends MPVervoermiddel {

    @OneOfInt({1, 2, 3, 4, 5, 9})
    @Schema(description = "De code die het soort vervoermiddel aangeeft.\n\n" +
            "- 1 = Auto\n" +
            "- 2 = Bromfiets\n" +
            "- 3 = Fiets\n" +
            "- 4 = Motor\n" +
            "- 5 = Openbaar vervoer\n" +
            "- 9 = Anders",
            type = "Integer",
            allowableValues = {"1", "2", "3", "4", "5", "9"}
    )
    private Integer codeVervoermiddel;

}
