package com.cap.pocvng.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
@Entity
public class ContactpersoonAfdeling {

    @Id
    @GeneratedValue
    @JsonIgnore
    Long id;

    @Size(max = 35)
    private String naamContactpersoonAfdeling;

    @Valid
    @ElementCollection
    private List<Telefoonnummer> telefoonnummer;

    @Valid
    @ElementCollection
    private List<Emailadres> emailadres;

}
