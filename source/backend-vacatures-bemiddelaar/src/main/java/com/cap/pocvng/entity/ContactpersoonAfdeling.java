package com.cap.pocvng.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * ContactpersoonAfdeling
 */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactpersoonAfdeling {

    @Size(max = 35)
    private String naamContactpersoonAfdeling;

    @Valid
    @ElementCollection
    private List<Telefoonummer> telefoonnummer;

    @Valid
    @ElementCollection
    private List<Emailadres> emailadres;

}
