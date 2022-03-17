package com.cap.pocvng.entity;

import com.cap.pocvng.validator.OneOfString;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

/**
 * Contractvorm
 */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Contractvorm {

    @Size(max = 1)
    @OneOfString({"B", "O"})
    @Schema(description = "De code die aangeeft of het arbeidscontract voor bepaalde of onbepaalde tijd van toepassing is.\n\n" +
            "- B = Bepaalde tijd\n" +
            "- O = Onbepaalde tijd",
            type = "String",
            allowableValues = {"B", "O"})
    private String codeTypeArbeidscontract;

    @Size(max = 2)
    @OneOfString({"01", "02", "03", "04"})
    @Schema(description = "De code die aangeeft welk type overeenkomst van toepassing is.\n\n" +
            "- 01 = Reguliere vacature\n" +
            "- 02 = Leerbaan\n" +
            "- 03 = Vrijwilligerswerk\n" +
            "- 04 = Werkervaringsplaats",
            type = "String",
            allowableValues = {"01", "02", "03", "04"})
    private String codeTypeOvereenkomst;

}
