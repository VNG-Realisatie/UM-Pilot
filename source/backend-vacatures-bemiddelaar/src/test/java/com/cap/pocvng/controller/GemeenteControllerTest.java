package com.cap.pocvng.controller;

import com.c4_soft.springaddons.security.oauth2.test.annotations.Claims;
import com.c4_soft.springaddons.security.oauth2.test.annotations.OpenIdClaims;
import com.c4_soft.springaddons.security.oauth2.test.annotations.StringClaim;
import com.c4_soft.springaddons.security.oauth2.test.annotations.keycloak.WithMockKeycloakAuth;
import com.cap.pocvng.dto.InlineResponse200;
import com.cap.pocvng.dto.VacatureMatchesRequestGemeente;
import com.cap.pocvng.entity.AanvraagVacature;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    private static VacatureMatchesRequestGemeente requestGemeente;
    private static InlineResponse200 response;
    private static Vacature vacature;
    private static AanvraagVacature aanvraagVacature;
    private static final String OIN = "123456789";
    private static final String INCORRECT_OIN = "INCORRECT_OIN";
    private static final String VUM_ID = "someid";
    private static final String VRAAG_ID = "somevraagid";
    private static final String PATH = "./src/test/resources/";

    @BeforeEach
    void setUp() {
        try {
            requestGemeente = objectMapper.readValue(new File(PATH, "vacature-matches-request-gemeente.json"),
                    VacatureMatchesRequestGemeente.class);
            response = objectMapper.readValue(new File(PATH, "inline-response200.json"),
                    InlineResponse200.class);
            vacature = objectMapper.readValue(new File(PATH, "vacature.json"),
                    Vacature.class);
            aanvraagVacature = objectMapper.readValue(new File(PATH, "aanvraag-vacature.json"),
                    AanvraagVacature.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @WithMockKeycloakAuth(authorities = {"ROLE_user"}, claims = @OpenIdClaims(otherClaims = @Claims(stringClaims = @StringClaim(name = "oin", value = OIN))))
    void getAanvraagVacatureWithCorrectOIN() throws Exception {
        when(service.findByVraagIdAndOin(VRAAG_ID, OIN)).thenReturn(aanvraagVacature);

        mockMvc.perform(get("/aanvraagvacature/" + OIN + "/" + VRAAG_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(aanvraagVacature)));
    }

    @Test
    @WithMockKeycloakAuth(authorities = {"ROLE_user"}, claims = @OpenIdClaims(otherClaims = @Claims(stringClaims = @StringClaim(name = "oin", value = INCORRECT_OIN))))
    void getAanvraagVacatureWithIncorrectOIN() throws Exception {

        mockMvc.perform(get("/aanvraagvacature/" + OIN + "/" + VRAAG_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andExpect(content().string("{\"code\":\"403.01\",\"message\":\"Geen authorizatie\",\"details\":\"403 FORBIDDEN \\\"Invalide OIN\\\"\"}"));
    }


    @Test
    @WithMockKeycloakAuth(authorities = {"ROLE_user"}, claims = @OpenIdClaims(otherClaims = @Claims(stringClaims = @StringClaim(name = "oin", value = OIN))))
    void createAanvraagVacatureWithCorrectOIN() throws Exception {
        when(service.makeRequestMatchesVum(any(VacatureMatchesRequestGemeente.class), eq(OIN))).thenReturn(response);

        mockMvc.perform(post("/aanvraagvacature/" + OIN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestGemeente)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(response)));
    }

    @Test
    @WithMockKeycloakAuth(authorities = {"ROLE_user"}, claims = @OpenIdClaims(otherClaims = @Claims(stringClaims = @StringClaim(name = "oin", value = INCORRECT_OIN))))
    void createAanvraagVacatureWithIncorrectOIN() throws Exception {
        mockMvc.perform(post("/aanvraagvacature/" + OIN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestGemeente)))
                .andExpect(status().isForbidden())
                .andExpect(content().string("{\"code\":\"403.01\",\"message\":\"Geen authorizatie\",\"details\":\"403 FORBIDDEN \\\"Invalide OIN\\\"\"}"));
    }

    @Test
    @WithMockKeycloakAuth(authorities = {"ROLE_user"}, claims = @OpenIdClaims(otherClaims = @Claims(stringClaims = @StringClaim(name = "oin", value = OIN))))
    void getDetailVacatureWithCorrectOIN() throws Exception {
        when(service.makeRequestDetailVacature(VRAAG_ID, VUM_ID, OIN)).thenReturn(vacature);

        mockMvc.perform(get("/aanvraagvacature/detail/" + OIN + "/" + VRAAG_ID + "/" + VUM_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(vacature)));
    }

    @Test
    @WithMockKeycloakAuth(authorities = {"ROLE_user"}, claims = @OpenIdClaims(otherClaims = @Claims(stringClaims = @StringClaim(name = "oin", value = INCORRECT_OIN))))
    void getDetailVacatureWithInCorrectOIN() throws Exception {

        mockMvc.perform(get("/aanvraagvacature/detail/" + OIN + "/" + VRAAG_ID + "/" + VUM_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andExpect(content().string("{\"code\":\"403.01\",\"message\":\"Geen authorizatie\",\"details\":\"403 FORBIDDEN \\\"Invalide OIN\\\"\"}"));
    }

    @Test
    @WithMockKeycloakAuth(authorities = {"ROLE_user"}, claims = @OpenIdClaims(otherClaims = @Claims(stringClaims = @StringClaim(name = "oin", value = OIN))))
    void getAllAanvraagVacaturesWithCorrectOIN() throws Exception {
        when(service.findAll(OIN)).thenReturn(List.of(aanvraagVacature));

        mockMvc.perform(get("/aanvraagvacature/lijst/" + OIN)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(List.of(aanvraagVacature))));
    }

    @Test
    @WithMockKeycloakAuth(authorities = {"ROLE_user"}, claims = @OpenIdClaims(otherClaims = @Claims(stringClaims = @StringClaim(name = "oin", value = INCORRECT_OIN))))
    void getAllAanvraagVacaturesWithInCorrectOIN() throws Exception {

        mockMvc.perform(get("/aanvraagvacature/lijst/" + OIN)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andExpect(content().string("{\"code\":\"403.01\",\"message\":\"Geen authorizatie\",\"details\":\"403 FORBIDDEN \\\"Invalide OIN\\\"\"}"));
    }


}