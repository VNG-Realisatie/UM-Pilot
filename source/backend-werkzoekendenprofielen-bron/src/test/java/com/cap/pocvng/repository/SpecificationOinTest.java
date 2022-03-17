package com.cap.pocvng.repository;

import com.cap.pocvng.config.ConfigProperties;
import com.cap.pocvng.dto.WerkzoekendeMatchingProfielen;
import com.cap.pocvng.dto.WerkzoekendeProfielMatchesRequest;
import com.cap.pocvng.entity.MPWerkzoekende;
import com.cap.pocvng.entity.Werkzoekende;
import com.cap.pocvng.mapper.SimpleMapper;
import com.cap.pocvng.service.VumService;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(ConfigProperties.class)
//Obtain additional beans for integration test.
@ComponentScan(basePackageClasses = {VumService.class, SimpleMapper.class})
@ActiveProfiles("test")
class SpecificationOinTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private VumService service;

    private static final String OIN = "123456789";
    private static final String differentOIN = "987654321";
    private static final String ID = "someid";


    private static WerkzoekendeProfielMatchesRequest werkzoekendeRequest;
    private static Werkzoekende werkzoekende;

    @BeforeEach
    void setUp() {
        werkzoekende = new Werkzoekende();
        werkzoekende.setIdWerkzoekende(ID);
        MPWerkzoekende requestWerkzoekende = new MPWerkzoekende();
        werkzoekendeRequest = new WerkzoekendeProfielMatchesRequest(requestWerkzoekende);
    }

    /**
     * Util method to persist and assert empty result.
     */
    private void assertEmpty(String oin) {
        entityManager.persistAndFlush(werkzoekende);
        // Find all werkzoekende with static OIN and modified request.
        ImmutablePair<Boolean, WerkzoekendeMatchingProfielen> result = service.match(werkzoekendeRequest, oin);
        assertThat(result.getRight().getMatches()).isEmpty();
    }

    /**
     * Util method to persist and assert a match.
     */
    private void assertFound(String oin) {
        entityManager.persistAndFlush(werkzoekende);
        // Find all werkzoekende with static OIN and modified request.
        ImmutablePair<Boolean, WerkzoekendeMatchingProfielen> result = service.match(werkzoekendeRequest, oin);
        assertThat(result.getRight().getMatches()).hasSize(1);
    }

    @Test
    void OinNullDoesNotMatch() {
        assertEmpty(null);
    }

    @Test
    void equalOinMatches() {
        werkzoekende.setOin(OIN);
        assertFound(OIN);
    }

    @Test
    void differentOinDoesNotMatch() {
        werkzoekende.setOin(OIN);
        assertEmpty(differentOIN);
    }


}