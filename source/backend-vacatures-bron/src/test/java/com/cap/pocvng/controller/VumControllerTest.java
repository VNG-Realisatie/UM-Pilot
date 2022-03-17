package com.cap.pocvng.controller;

import com.cap.pocvng.dto.InlineResponse200;
import com.cap.pocvng.dto.VacatureMatchesRequest;
import com.cap.pocvng.dto.VacatureMatchingProfielen;
import com.cap.pocvng.entity.MPVacature;
import com.cap.pocvng.entity.MPVacatureMatch;
import com.cap.pocvng.entity.Vacature;
import com.cap.pocvng.service.VumService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.KeycloakSecurityComponents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = VumController.class)
@ComponentScan(basePackageClasses = {KeycloakSecurityComponents.class, KeycloakSpringBootConfigResolver.class})
@ActiveProfiles("test")
class VumControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private VumService service;

    private static final String ID = "exampleid";
    private static final String OIN = "123456789";
    private static final String OIN2 = "987654321";
    private static final String PATH = "./src/test/resources/";

    private static Vacature vacature;
    private static MPVacatureMatch mpVacatureMatch;


    @BeforeEach
    void setUp() {
        try {
            vacature = objectMapper.readValue(new File(PATH, "vacature.json"), Vacature.class);
            mpVacatureMatch = objectMapper.readValue(new File(PATH, "mp-vacature.json"), MPVacatureMatch.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getVacatureFound() throws Exception {
        when(service.findById(ID, OIN)).thenReturn(Optional.ofNullable(vacature));

        mockMvc.perform(TestRequestFactory.getWithHeaders("/vacatures/" + ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(vacature)));
    }

    @Test
    void getVacatureNotFound() throws Exception {
        when(service.findById(ID, OIN)).thenReturn(Optional.empty());

        mockMvc.perform(TestRequestFactory.getWithHeaders("/vacatures/" + ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("{\"code\":\"400.01\",\"message\":\"Ongeldige aanroep\",\"details\":\"ID niet gevonden\"}"));
    }

    @Test
    void matchVacatureFound() throws Exception {

        VacatureMatchesRequest request = new VacatureMatchesRequest(new MPVacature());
        VacatureMatchingProfielen response = new VacatureMatchingProfielen(List.of(new MPVacatureMatch[]{mpVacatureMatch}));


        when(service.match(any(), eq(OIN), eq(OIN2))).thenReturn(ImmutablePair.of(false, response));

        InlineResponse200 inlineResponse = new InlineResponse200(false, response);

        mockMvc.perform(TestRequestFactory.postWithHeaders("/vacatures/matches")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(inlineResponse)));

    }

    @Test
    void matchVacatureEmpty() throws Exception {

        VacatureMatchesRequest request = new VacatureMatchesRequest(new MPVacature());
        VacatureMatchingProfielen response = new VacatureMatchingProfielen(List.of(new MPVacatureMatch[]{}));

        when(service.match(any(), eq(OIN), eq(OIN2))).thenReturn(ImmutablePair.of(false, response));

        InlineResponse200 inlineResponse = new InlineResponse200(false, response);

        mockMvc.perform(TestRequestFactory.postWithHeaders("/vacatures/matches")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(inlineResponse)));

    }
}