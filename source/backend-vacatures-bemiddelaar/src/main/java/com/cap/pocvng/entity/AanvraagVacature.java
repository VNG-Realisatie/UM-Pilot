package com.cap.pocvng.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AanvraagVacature {

    @Size(max = 100)
    @Id
    private String vraagId;


    @OneToMany( //TODO: Many to many requires less data, but has orphan removal problem.
            mappedBy = "aanvraagVacatures",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<MPVacatureMatch> vacatures = new HashSet<>();

    @JsonIgnore
    private String oin;

    @NotBlank
    private String aanvraagKenmerk;

    private int timesAccessLeft;

    private LocalDate creatieDatum = LocalDate.now();

    public AanvraagVacature(String vraagId, String oin, String aanvraagKenmerk, Integer timesAccessLeft) {
        this.vraagId = vraagId;
        this.oin = oin;
        this.aanvraagKenmerk = aanvraagKenmerk;
        this.timesAccessLeft = timesAccessLeft;
    }

    public void addVacature(MPVacatureMatch vacature) {
        vacatures.add(vacature);
        vacature.setAanvraagVacatures(this);
    }

    public void removeVacature(MPVacatureMatch vacature) {
        vacatures.remove(vacature);
        vacature.setAanvraagVacatures(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AanvraagVacature aanvraagVacature1 = (AanvraagVacature) o;
        return Objects.equals(vraagId, aanvraagVacature1.vraagId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vraagId);
    }
}
