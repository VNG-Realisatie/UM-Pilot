package com.cap.pocvng.mapper;

import com.cap.pocvng.dto.VacatureMatchesRequest;
import com.cap.pocvng.dto.VacatureMatchesRequestGemeente;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
@ActiveProfiles("test")
class SimpleMapperTest {

    private static final SimpleMapper mapper = new SimpleMapperImpl();

    private static VacatureMatchesRequestGemeente requestGemeente;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String PATH = "./src/test/resources/";

    @BeforeEach
    void setUp() {
        try {
            // get the request as an object from the json file.
            requestGemeente = objectMapper.readValue(new File(PATH, "vacature-matches-request-gemeente.json"), VacatureMatchesRequestGemeente.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void vacatureMatchesRequestGemeenteToVacatureMatchesRequest() {
        VacatureMatchesRequest request = mapper.vacatureMatchesRequestGemeenteToVacatureMatchesRequest(requestGemeente);

        assertThat(request.getCallbackURL()).isNull();
        assertThat(request.getPostcode()).isEqualTo(requestGemeente.getPostcode());
        assertThat(request.getStraal()).isEqualTo(requestGemeente.getStraal());
        assertThat(request.getVraagObject()).isEqualTo(requestGemeente.getVraagObject());
    }

    @Test
    void vacatureMatchesRequestGemeenteNull() {
        requestGemeente = null;
        VacatureMatchesRequest request = mapper.vacatureMatchesRequestGemeenteToVacatureMatchesRequest(requestGemeente);
        assertThat(request).isNull();
    }
}