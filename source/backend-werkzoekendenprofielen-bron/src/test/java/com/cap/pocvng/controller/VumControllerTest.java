package com.cap.pocvng.controller;

import com.cap.pocvng.dto.InlineResponse200;
import com.cap.pocvng.dto.WerkzoekendeMatchingProfielen;
import com.cap.pocvng.dto.WerkzoekendeProfielMatchesRequest;
import com.cap.pocvng.entity.MPWerkzoekende;
import com.cap.pocvng.entity.MPWerkzoekendeMatch;
import com.cap.pocvng.entity.Werkzoekende;
import com.cap.pocvng.service.ElkService;
import com.cap.pocvng.service.VumService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
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

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


// To make loadFile instance and make use of objectmapper.
@TestInstance(PER_CLASS)
@WebMvcTest(controllers = VumController.class)
@ComponentScan(basePackageClasses = {KeycloakSecurityComponents.class, KeycloakSpringBootConfigResolver.class})
@ActiveProfiles("test")
class VumControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private VumService vumService;

    @MockBean
    private ElkService elkService;

    private static final String ID = "exampleid";
    private static final String OIN = "123456789";
    private static final String OIN2 = "987654321";
    private static final String PATH = "./src/test/resources/";

    private static MPWerkzoekendeMatch mpWerkzoekendeMatch;
    private static WerkzoekendeProfielMatchesRequest werkzoekendeRequest;
    private static Werkzoekende werkzoekende;
    private static MPWerkzoekende mpWerkzoekende;
    private static WerkzoekendeMatchingProfielen werkzoekendeMatchingProfielen;


    @BeforeAll
    void loadFile() {
        try {
            werkzoekende = objectMapper.readValue(new File(PATH, "test-user.json"), Werkzoekende.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    void setUp() {
        mpWerkzoekendeMatch = new MPWerkzoekendeMatch();
        werkzoekendeMatchingProfielen = new WerkzoekendeMatchingProfielen();
        mpWerkzoekende = new MPWerkzoekende();
        werkzoekendeRequest = new WerkzoekendeProfielMatchesRequest();
    }


    @Test
    void getWerkzoekendeFound() throws Exception {
        when(vumService.findById(ID, OIN)).thenReturn(Optional.ofNullable(werkzoekende));

        mockMvc.perform(TestRequestFactory.getWithHeaders("/werkzoekendeProfielen/" + ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(werkzoekende)));
    }

    @Test
    void getWerkzoekendeNotFound() throws Exception {
        when(vumService.findById(ID, OIN)).thenReturn(Optional.empty());

        mockMvc.perform(TestRequestFactory.getWithHeaders("/werkzoekendeProfielen/" + ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("{\"code\":\"400.01\",\"message\":\"Ongeldige aanroep\",\"details\":\"ID niet gevonden\"}"));
    }

    @Test
    void matchWerkzoekendeProfielenFound() throws Exception {

        werkzoekendeRequest.setVraagObject(mpWerkzoekende);
        werkzoekendeMatchingProfielen.setMatches(List.of(mpWerkzoekendeMatch));

        when(vumService.match(any(), eq(OIN))).thenReturn(ImmutablePair.of(false, werkzoekendeMatchingProfielen));

        InlineResponse200 response = new InlineResponse200(false, werkzoekendeMatchingProfielen);

        mockMvc.perform(TestRequestFactory.postWithHeaders("/werkzoekendeProfielen/matches")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(werkzoekendeRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(response)));

    }

    @Test
    void matchWerkzoekendeProfielenEmpty() throws Exception {

        werkzoekendeRequest.setVraagObject(mpWerkzoekende);
        werkzoekendeMatchingProfielen.setMatches(List.of());

        when(vumService.match(any(), eq(OIN))).thenReturn(ImmutablePair.of(false, werkzoekendeMatchingProfielen));

        InlineResponse200 response = new InlineResponse200(false, werkzoekendeMatchingProfielen);

        mockMvc.perform(TestRequestFactory.postWithHeaders("/werkzoekendeProfielen/matches")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(werkzoekendeRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(response)));

    }


}