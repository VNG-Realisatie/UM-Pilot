package com.cap.pocvng.controller;

import com.c4_soft.springaddons.security.oauth2.test.annotations.Claims;
import com.c4_soft.springaddons.security.oauth2.test.annotations.OpenIdClaims;
import com.c4_soft.springaddons.security.oauth2.test.annotations.StringClaim;
import com.c4_soft.springaddons.security.oauth2.test.annotations.keycloak.WithMockKeycloakAuth;
import com.cap.pocvng.entity.Werkzoekende;
import com.cap.pocvng.service.GemeenteService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// To make loadFile instance and make use of objectmapper.
@TestInstance(PER_CLASS)
@WebMvcTest(controllers = GemeenteController.class)
@ComponentScan(basePackageClasses = {KeycloakSecurityComponents.class, KeycloakSpringBootConfigResolver.class})
@ActiveProfiles("test")
class GemeenteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private GemeenteService service;


    private static List<Werkzoekende> werkzoekendeList;
    private static String werkzoekendeListJson;

    private static final String ID = "exampleid";
    private static final String OIN = "123456789";

    private static final String PATH = "./src/test/resources/";


    @BeforeAll
    void loadFile() {
        try {
            // get the werzoekende as an object from the json file.
            werkzoekendeList = objectMapper.readValue(new File(PATH, "test-user-list.json"), new TypeReference<List<Werkzoekende>>() {
            });
            // get the werkzoekende as a String for the Json request.
            werkzoekendeListJson = new String(Files.readAllBytes(Paths.get(PATH, "test-user-list.json")));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    @WithMockKeycloakAuth(authorities = {"ROLE_user"}, claims = @OpenIdClaims(otherClaims = @Claims(stringClaims = @StringClaim(name = "oin", value = OIN))))
    void postValidWerkzoekendeListWithCorrectOIN() throws Exception { //TODO: webmvc does not test validation
        when(service.saveAll(anyList(), eq(OIN))).thenReturn(werkzoekendeList);

        mockMvc.perform(post("/werkzoekende/lijst/" + OIN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(werkzoekendeListJson))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(werkzoekendeList)));


    }

    @Test
    @WithMockKeycloakAuth(authorities = {"ROLE_user"}, claims = @OpenIdClaims(otherClaims = @Claims(stringClaims = @StringClaim(name = "oin", value = "WRONG OIN"))))
    void postValidWerkzoekendeListWithIncorrectOIN() throws Exception {
        mockMvc.perform(post("/werkzoekende/lijst/" + OIN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(werkzoekendeListJson))
                .andExpect(status().isForbidden())
                .andExpect(content().string("{\"code\":\"403.01\",\"message\":\"Geen authorizatie\",\"details\":\"403 FORBIDDEN \\\"Invalide OIN\\\"\"}"));


    }


}




