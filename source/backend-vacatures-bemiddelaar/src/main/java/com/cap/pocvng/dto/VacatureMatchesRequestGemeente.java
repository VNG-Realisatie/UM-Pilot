package com.cap.pocvng.dto;

import com.cap.pocvng.entity.MPVacature;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VacatureMatchesRequestGemeente {
    @NotNull
    @Pattern(regexp = "^[1-9][0-9]{3}(?!sa|sd|ss)[a-z]{2}$", flags ={Pattern.Flag.CASE_INSENSITIVE})
    String postcode;

    @NotNull
    @Min(0)
    @Max(999)
    Integer straal;

    @NotBlank
    String aanvraagKenmerk;

    @NotNull
    @Valid
    MPVacature vraagObject;
}
