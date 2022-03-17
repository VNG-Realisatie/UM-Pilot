package com.cap.pocvng.controller;

import com.c4_soft.springaddons.security.oauth2.test.annotations.Claims;
import com.c4_soft.springaddons.security.oauth2.test.annotations.OpenIdClaims;
import com.c4_soft.springaddons.security.oauth2.test.annotations.StringClaim;
import com.c4_soft.springaddons.security.oauth2.test.annotations.keycloak.WithMockKeycloakAuth;
import com.cap.pocvng.entity.Vacature;
import com.cap.pocvng.service.GemeenteService;
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
import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    private static Vacature vacature;
    private static String vacatureListJson;

    private static final String ID = "exampleid";
    private static final String OIN = "123456789";

    private static final String PATH = "./src/test/resources/";


    @BeforeEach
    void setUp() {
        try {
            // get the vacature as an object from the json file.
            vacature = objectMapper.readValue(new File(PATH, "vacature.json"), Vacature.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @WithMockKeycloakAuth(authorities = {"ROLE_user"}, claims = @OpenIdClaims(otherClaims = @Claims(stringClaims = @StringClaim(name = "oin", value = OIN))))
    void postValidVacatureListWithCorrectOIN() throws Exception { //TODO: webmvc does not test validation
        when(service.saveAll(anyList(), eq(OIN))).thenReturn(List.of(vacature));

        mockMvc.perform(post("/vacature/lijst/" + OIN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(List.of(vacature))))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(List.of(vacature))));


    }

    @Test
    @WithMockKeycloakAuth(authorities = {"ROLE_user"}, claims = @OpenIdClaims(otherClaims = @Claims(stringClaims = @StringClaim(name = "oin", value = "WRONG OIN"))))
    void postValidVacatureListWithIncorrectOIN() throws Exception {
        mockMvc.perform(post("/vacature/lijst/" + OIN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(List.of(vacature))))
                .andExpect(status().isForbidden())
                .andExpect(content().string("{\"code\":\"403.01\",\"message\":\"Geen authorizatie\",\"details\":\"403 FORBIDDEN \\\"Invalide OIN\\\"\"}"));


    }
}