package com.cap.pocvng.controller;

import com.cap.pocvng.dto.WerkzoekendeProfielMatchesCallbackRequest;
import com.cap.pocvng.service.VumService;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    private static final String PATH = "./src/test/resources/";
    private static WerkzoekendeProfielMatchesCallbackRequest callbackRequest;

    @BeforeEach
    void setUp() {
        try {
            callbackRequest = objectMapper.readValue(new File(PATH, "werkzoekende-profiel-matches-callback-request.json"),
                    WerkzoekendeProfielMatchesCallbackRequest.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void callbackWithHeaders() throws Exception {

        mockMvc.perform(TestRequestFactory.postWithHeaders("/callback")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(callbackRequest)))
                .andExpect(status().isOk());

    }

    @Test
    void callbackWithoutHeaders() throws Exception {
        mockMvc.perform(post("/callback")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(callbackRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Required request header")));
    }
}