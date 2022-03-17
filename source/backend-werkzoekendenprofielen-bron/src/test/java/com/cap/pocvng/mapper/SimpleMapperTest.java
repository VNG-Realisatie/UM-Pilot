package com.cap.pocvng.mapper;

import com.cap.pocvng.entity.Arbeidsmarktkwalificatie;
import com.cap.pocvng.entity.MPArbeidsmarktkwalificatie;
import com.cap.pocvng.entity.MPWerkzoekendeMatch;
import com.cap.pocvng.entity.Werkzoekende;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest // required to autowire objectmapper
@ActiveProfiles("test")
class SimpleMapperTest {

    private static SimpleMapperImpl mapper;

    private static Werkzoekende werkzoekende;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String PATH = "./src/test/resources/";


    @BeforeEach
    void setUp() {
        mapper = new SimpleMapperImpl();
        try {
            // get the werzoekende as an object from the json file.
            werkzoekende = objectMapper.readValue(new File(PATH, "test-user.json"), Werkzoekende.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    void testWerkzoekendeToMPWerkzoekendeMatch() {
        MPWerkzoekendeMatch result = mapper.werkzoekendeToMPWerkzoekendeMatch(werkzoekende);

        assertThat(result.getIdWerkzoekende()).isEqualTo(werkzoekende.getIdWerkzoekende());
        assertThat(result.getIndicatieLdrRegistratie()).isEqualTo(werkzoekende.getIndicatieLdrRegistratie());
        assertThat(result.getIndicatieBeschikbaarheidContactgegevens())
                .isEqualTo(werkzoekende.getIndicatieBeschikbaarheidContactgegevens());
        assertThat(result.getVoorkeursland()).hasSameElementsAs(werkzoekende.getVoorkeursland());
        assertThat(result.getVervoermiddel()).hasSameElementsAs(werkzoekende.getVervoermiddel());
        assertThat(result.getMobiliteit()).isEqualTo(werkzoekende.getMobiliteit());
        assertThat(result.getWerktijden()).isEqualTo(werkzoekende.getWerktijden());
        assertThat(result.getContractvorm()).hasSameElementsAs(werkzoekende.getContractvorm());
        assertThat(result.getSector()).hasSameElementsAs(werkzoekende.getSector());
        assertThat(result.getBemiddelingsberoep()).hasSameElementsAs(werkzoekende.getBemiddelingsberoep());
    }

    @Test
    void ArbeidskwalificatieToMpWerkzoekendeMatch() {

        MPWerkzoekendeMatch result = mapper.werkzoekendeToMPWerkzoekendeMatch(werkzoekende);
        Arbeidsmarktkwalificatie werkzoekendeA = werkzoekende.getArbeidsmarktkwalificatie();
        MPArbeidsmarktkwalificatie MPWerkzoekendeA = result.getArbeidsmarktkwalificatie();

        assertThat(MPWerkzoekendeA.getCodeWerkEnDenkniveauWerkzoekende())
                .isEqualTo(werkzoekendeA.getCodeWerkEnDenkniveauWerkzoekende());
        assertThat(MPWerkzoekendeA.getVakvaardigheid()).hasSameElementsAs(werkzoekendeA.getVakvaardigheid());
        assertThat(MPWerkzoekendeA.getTaalbeheersing()).hasSameElementsAs(werkzoekendeA.getTaalbeheersing());
        assertThat(MPWerkzoekendeA.getGedragscompetentie()).hasSameElementsAs(werkzoekendeA.getGedragscompetentie());
        assertThat(MPWerkzoekendeA.getRijbewijs()).hasSameElementsAs(werkzoekendeA.getRijbewijs());
        assertThat(MPWerkzoekendeA.getCursus()).hasSameElementsAs(werkzoekendeA.getCursus());
        assertThat(MPWerkzoekendeA.getOpleiding()).hasSameElementsAs(werkzoekendeA.getOpleiding());
        assertThat(MPWerkzoekendeA.getWerkervaring()).hasSameElementsAs(werkzoekendeA.getWerkervaring());
    }

    @Test
    void nullWerkzoekendeToMPWerkzoekende() {
        werkzoekende = null;
        MPWerkzoekendeMatch result = mapper.werkzoekendeToMPWerkzoekendeMatch(werkzoekende);

        assertThat(result).isNull();
    }

    @Test
    void MPVervoersmiddelWerkzoekendeNull() {
        werkzoekende.setVervoermiddel(null);

        MPWerkzoekendeMatch result = mapper.werkzoekendeToMPWerkzoekendeMatch(werkzoekende);

        assertThat(result.getVervoermiddel()).isNull();
    }

    @Test
    void MPCursisListNull() {
        werkzoekende.getArbeidsmarktkwalificatie().setCursus(null);

        MPWerkzoekendeMatch result = mapper.werkzoekendeToMPWerkzoekendeMatch(werkzoekende);

        assertThat(result.getArbeidsmarktkwalificatie().getCursus()).isNull();
    }

    @Test
    void MPOpleidingNull() {
        werkzoekende.getArbeidsmarktkwalificatie().setOpleiding(null);

        MPWerkzoekendeMatch result = mapper.werkzoekendeToMPWerkzoekendeMatch(werkzoekende);

        assertThat(result.getArbeidsmarktkwalificatie().getOpleiding()).isNull();

    }

    @Test
    void MPWerkervaringNull() {
        werkzoekende.getArbeidsmarktkwalificatie().setWerkervaring(null);

        MPWerkzoekendeMatch result = mapper.werkzoekendeToMPWerkzoekendeMatch(werkzoekende);

        assertThat(result.getArbeidsmarktkwalificatie().getWerkervaring()).isNull();
    }

    @Test
    void MPArbeidsmarktkwalificatieNull() {
        werkzoekende.setArbeidsmarktkwalificatie(null);

        MPWerkzoekendeMatch result = mapper.werkzoekendeToMPWerkzoekendeMatch(werkzoekende);

        assertThat(result.getArbeidsmarktkwalificatie()).isNull();

    }
}