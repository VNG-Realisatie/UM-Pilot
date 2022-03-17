package com.cap.pocvng.repository;

import com.cap.pocvng.config.ConfigProperties;
import com.cap.pocvng.dto.VacatureMatchesRequest;
import com.cap.pocvng.dto.VacatureMatchingProfielen;
import com.cap.pocvng.entity.MPVacature;
import com.cap.pocvng.entity.Vacature;
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
@ComponentScan(basePackageClasses = {VumService.class, SimpleMapper.class})
@ActiveProfiles("test")
class SpecificationOinTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private VumService service;


    private static final String OIN = "123456789";
    private static final String differentOIN = "987654321";
    private static VacatureMatchesRequest vacatureRequest;
    private static Vacature vacature;

    @BeforeEach
    void setUp() {
        vacature = new Vacature();
        MPVacature mpVacature = new MPVacature();
        vacatureRequest = new VacatureMatchesRequest(mpVacature);

    }



    /**
     * Util method to persist and assert empty result.
     */
    private void assertEmpty(String oin) {
        entityManager.persistAndFlush(vacature);
        ImmutablePair<Boolean, VacatureMatchingProfielen> result = service.match(vacatureRequest, oin, differentOIN);
        assertThat(result.getRight().getMatches()).isEmpty();
        entityManager.flush();
    }

    /**
     * Util method to persist and assert a match.
     */
    private void assertFound(String oin) {
        entityManager.persistAndFlush(vacature);
        ImmutablePair<Boolean, VacatureMatchingProfielen> result = service.match(vacatureRequest, oin, differentOIN);
        assertThat(result.getRight().getMatches()).hasSize(1);
    }

    @Test
    void OinNullDoesNotMatch() {
        assertEmpty(null);
    }

    @Test
    void equalOinMatches() {
        vacature.setOin(OIN);
        assertFound(OIN);
    }

    @Test
    void differentOinDoesNotMatch() {
        vacature.setOin(OIN);
        assertEmpty(differentOIN);
    }
}
