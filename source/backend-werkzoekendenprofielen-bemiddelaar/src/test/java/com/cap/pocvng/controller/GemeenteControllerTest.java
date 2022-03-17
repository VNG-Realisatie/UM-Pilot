package com.cap.pocvng.controller;

import com.c4_soft.springaddons.security.oauth2.test.annotations.Claims;
import com.c4_soft.springaddons.security.oauth2.test.annotations.OpenIdClaims;
import com.c4_soft.springaddons.security.oauth2.test.annotations.StringClaim;
import com.c4_soft.springaddons.security.oauth2.test.annotations.keycloak.WithMockKeycloakAuth;
import com.cap.pocvng.dto.InlineResponse200;
import com.cap.pocvng.dto.WerkzoekendeProfielMatchesRequestGemeente;
import com.cap.pocvng.entity.AanvraagWerkzoekende;
import com.cap.pocvng.entity.Werkzoekende;
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

    private static InlineResponse200 response;
    private static WerkzoekendeProfielMatchesRequestGemeente requestGemeente;
    private static AanvraagWerkzoekende aanvraagWerkzoekende;
    private static Werkzoekende werkzoekende;
    private static final String OIN = "123456789";
    private static final String INCORRECT_OIN = "INCORRECT_OIN";
    private static final String VUM_ID = "someid";
    private static final String VRAAG_ID = "somevraagid";
    private static final String PATH = "./src/test/resources/";

    @BeforeEach
    void setUp() {
        try {
            requestGemeente = objectMapper.readValue(new File(PATH, "werkzoekende-profiel-matches-request-gemeente.json"),
                    WerkzoekendeProfielMatchesRequestGemeente.class);
            response = objectMapper.readValue(new File(PATH, "inline-response200.json"),
                    InlineResponse200.class);
            aanvraagWerkzoekende = objectMapper.readValue(new File(PATH, "aanvraag-werkzoekende.json"),
                    AanvraagWerkzoekende.class);
            werkzoekende = objectMapper.readValue(new File(PATH, "werkzoekende.json"),
                    Werkzoekende.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @WithMockKeycloakAuth(authorities = {"ROLE_user"}, claims = @OpenIdClaims(otherClaims = @Claims(stringClaims = @StringClaim(name = "oin", value = OIN))))
    void getAanvraagWerkzoekendeWithCorrectOIN() throws Exception {
        when(service.findByVraagIdAndOin(VRAAG_ID, OIN)).thenReturn(aanvraagWerkzoekende);

        mockMvc.perform(get("/aanvraagwerkzoekende/" + OIN + "/" + VRAAG_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(aanvraagWerkzoekende)));
    }

    @Test
    @WithMockKeycloakAuth(authorities = {"ROLE_user"}, claims = @OpenIdClaims(otherClaims = @Claims(stringClaims = @StringClaim(name = "oin", value = INCORRECT_OIN))))
    void getAanvraagWerkzoekendeWithIncorrectOIN() throws Exception {

        mockMvc.perform(get("/aanvraagwerkzoekende/" + OIN + "/" + VRAAG_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andExpect(content().string("{\"code\":\"403.01\",\"message\":\"Geen authorizatie\",\"details\":\"403 FORBIDDEN \\\"Invalide OIN\\\"\"}"));


    }

    @Test
    @WithMockKeycloakAuth(authorities = {"ROLE_user"}, claims = @OpenIdClaims(otherClaims = @Claims(stringClaims = @StringClaim(name = "oin", value = OIN))))
    void createAanvraagWerkzoekendeWithCorrectOIN() throws Exception {
        when(service.makeRequestMatchesVum(any(WerkzoekendeProfielMatchesRequestGemeente.class), eq(OIN))).thenReturn(response);

        mockMvc.perform(post("/aanvraagwerkzoekende/" + OIN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestGemeente)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(response)));
    }

    @Test
    @WithMockKeycloakAuth(authorities = {"ROLE_user"}, claims = @OpenIdClaims(otherClaims = @Claims(stringClaims = @StringClaim(name = "oin", value = INCORRECT_OIN))))
    void createAanvraagWerkzoekendeWithIncorrectOIN() throws Exception {
        mockMvc.perform(post("/aanvraagwerkzoekende/" + OIN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestGemeente)))
                .andExpect(status().isForbidden())
                .andExpect(content().string("{\"code\":\"403.01\",\"message\":\"Geen authorizatie\",\"details\":\"403 FORBIDDEN \\\"Invalide OIN\\\"\"}"));
    }

    @Test
    @WithMockKeycloakAuth(authorities = {"ROLE_user"}, claims = @OpenIdClaims(otherClaims = @Claims(stringClaims = @StringClaim(name = "oin", value = OIN))))
    void getDetailWerkzoekendeWithCorrectOIN() throws Exception {
        when(service.makeRequestDetailWerkzoekende(VRAAG_ID, VUM_ID, OIN)).thenReturn(werkzoekende);

        mockMvc.perform(get("/aanvraagwerkzoekende/detail/" + OIN + "/" + VRAAG_ID + "/" + VUM_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(werkzoekende)));
    }

    @Test
    @WithMockKeycloakAuth(authorities = {"ROLE_user"}, claims = @OpenIdClaims(otherClaims = @Claims(stringClaims = @StringClaim(name = "oin", value = INCORRECT_OIN))))
    void getDetailWerkzoekendeWithInCorrectOIN() throws Exception {
        mockMvc.perform(get("/aanvraagwerkzoekende/detail/" + OIN + "/" + VRAAG_ID + "/" + VUM_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andExpect(content().string("{\"code\":\"403.01\",\"message\":\"Geen authorizatie\",\"details\":\"403 FORBIDDEN \\\"Invalide OIN\\\"\"}"));
    }

    @Test
    @WithMockKeycloakAuth(authorities = {"ROLE_user"}, claims = @OpenIdClaims(otherClaims = @Claims(stringClaims = @StringClaim(name = "oin", value = OIN))))
    void getAllAanvraagWerkzoekndenWithCorrectOIN() throws Exception {
        when(service.findAll(OIN)).thenReturn(List.of(aanvraagWerkzoekende));

        mockMvc.perform(get("/aanvraagwerkzoekende/lijst/" + OIN)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(List.of(aanvraagWerkzoekende))));
    }

    @Test
    @WithMockKeycloakAuth(authorities = {"ROLE_user"}, claims = @OpenIdClaims(otherClaims = @Claims(stringClaims = @StringClaim(name = "oin", value = INCORRECT_OIN))))
    void getAllAanvraagWerkzoekendenWithInCorrectOIN() throws Exception {

        mockMvc.perform(get("/aanvraagwerkzoekende/lijst/" + OIN)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andExpect(content().string("{\"code\":\"403.01\",\"message\":\"Geen authorizatie\",\"details\":\"403 FORBIDDEN \\\"Invalide OIN\\\"\"}"));
    }

}