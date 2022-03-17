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
public class AanvraagWerkzoekende {

    @Size(max = 100)
    @Id
    private String vraagId;

    @OneToMany( //TODO: Many to many requires less data, but has orphan removal problem.
            mappedBy = "aanvraagWerkzoekenden",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<MPWerkzoekendeMatch> werkzoekenden = new HashSet<>();

    @JsonIgnore
    private String oin;

    @NotBlank
    private String aanvraagKenmerk;

    private int timesAccessLeft;

    private LocalDate creatieDatum = LocalDate.now();

    public AanvraagWerkzoekende(String vraagId, String oin, String aanvraagKenmerk, int timesAccessLeft) {
        this.vraagId = vraagId;
        this.oin = oin;
        this.aanvraagKenmerk = aanvraagKenmerk;
        this.timesAccessLeft = timesAccessLeft;
    }

    public void addWerkzoekende(MPWerkzoekendeMatch werkzoekendeMatch) {
        werkzoekenden.add(werkzoekendeMatch);
        werkzoekendeMatch.setAanvraagWerkzoekenden(this);
    }

    public void removeWerkzoekende(MPWerkzoekendeMatch werkzoekendeMatch) {
        werkzoekenden.remove(werkzoekendeMatch);
        werkzoekendeMatch.setAanvraagWerkzoekenden(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AanvraagWerkzoekende that = (AanvraagWerkzoekende) o;
        return Objects.equals(vraagId, that.vraagId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vraagId);
    }
}
