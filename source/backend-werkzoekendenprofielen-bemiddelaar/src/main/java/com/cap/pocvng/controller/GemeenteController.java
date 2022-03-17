package com.cap.pocvng.controller;

import com.cap.pocvng.dto.InlineResponse200;
import com.cap.pocvng.dto.WerkzoekendeProfielMatchesRequestGemeente;
import com.cap.pocvng.entity.AanvraagWerkzoekende;
import com.cap.pocvng.entity.Werkzoekende;
import com.cap.pocvng.exception.WrongOinException;
import com.cap.pocvng.service.GemeenteService;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@Validated
@RestController
@Slf4j
public class GemeenteController {

    private final GemeenteService service;

    public GemeenteController(GemeenteService service) {
        this.service = service;
    }

    /**
     * GET /aanvraagwerkzoekende/lijst/{oin} : Endpoint to retrieve all outstanding aanvragen for the given OIN.
     *
     * @param oin identifier of municipality
     * @return OK (status code 200)
     * or Bad request (status code 400)
     * or not authorized (status code 401)
     * or Forbidden (status code 403)
     * or Internal Server Error (status code 500)
     */
    @GetMapping("/aanvraagwerkzoekende/lijst/{oin}")
    public List<AanvraagWerkzoekende> allAanvraagWerkzoekendes(@PathVariable String oin) {
        log.info("Verzoek voor alle uitstaande werkzoekenden aanvragen van OIN: " + oin);
        if (getOinUser().equals(oin)) {
            List<AanvraagWerkzoekende> result = service.findAll(oin);
            log.info("Succesvolle verzoek voor alle uitstaande werkzoekenden aanvragen van OIN: " + oin);
            return result;
        } else {
            log.info("Mislukte verzoek voor alle uitstaande werkzoekenden door OIN: " + getOinUser() + "Er is geprobeerd te uploaden voor OIN:" + oin);
            throw new WrongOinException();
        }
    }

    /**
     * GET /aanvraagwerkzoekende/{oin}/{vraagId} : Endpoint to retrieve AanvraagWerkzoekende for the given OIN with vraag ID.
     *
     * @param oin     identifier of municipality
     * @param vraagId identifier of AanvraagWerkzoekende.
     * @return OK (status code 200)
     * or Bad request (status code 400)
     * or not authorized (status code 401)
     * or Forbidden (status code 403)
     * or Unprocessable request (status code 422)
     * or Internal Server Error (status code 500)
     */
    @GetMapping("/aanvraagwerkzoekende/{oin}/{vraagId}")
    public AanvraagWerkzoekende getAanvraagWerkzoekende(@PathVariable String oin, @PathVariable String vraagId) {
        log.info("Verzoek voor werkzoekende met vraagID: " + vraagId + " van OIN: " + oin);
        if (getOinUser().equals(oin)) {
            AanvraagWerkzoekende result = service.findByVraagIdAndOin(vraagId, oin);
            log.info("Succesvolle verzoek voor werkzoekende met vraagID: " + vraagId + " van OIN: " + oin);
            return result;
        } else {
            log.info("Mislukte verzoek voor werkzoekende met vraagID: " + vraagId + " van OIN: " + oin);
            throw new WrongOinException();
        }
    }


    /**
     * GET /aanvraagwerkzoekende/{oin}/{vumId} : Endpoint to retrieve detailed werkzoekende for given vumID.
     *
     * @param oin     identifier of municipality
     * @param vraagId identifier of the AanvraagWerkzoekende
     * @param vumId   identifier of werkzoekende
     * @return OK (status code 200)
     * or Bad request (status code 400)
     * or not authorized (status code 401)
     * or Forbidden (status code 403)
     * or Unprocessable request (status code 422)
     * or Internal Server Error (status code 500)
     */
    @GetMapping("/aanvraagwerkzoekende/detail/{oin}/{vraagId}/{vumId}")
    public Werkzoekende getDetailWerkzoekende(@PathVariable String oin, @PathVariable String vraagId, @PathVariable String vumId) {
        log.info("Verzoek voor detail werkzoekende met vraagID: " + vraagId + " vumID: " + vumId + " van OIN: " + oin);
        if (getOinUser().equals(oin)) {
            Werkzoekende result = service.makeRequestDetailWerkzoekende(vraagId, vumId, oin);
            log.info("Succesvolle verzoek voor detail werkzoekende met vraagID: " + vraagId + " vumID: " + vumId + " van OIN: " + oin);
            return result;
        } else {
            log.info("Mislukte verzoek voor detail werkzoekende met vraagID: " + vraagId + " vumID: " + vumId + " van OIN: " + oin);
            throw new WrongOinException();
        }
    }


    /**
     * POST /aanvraagwerkzoekende/{oin} : Endpoint to post a request for werkzoekenden matching aanvraagWerkzoekende.
     *
     * @param aanvraagWerkzoekende AanvraagWerkzoekende to match against
     * @param oin                  identifier of municipality
     * @return OK (status code 200)
     * or Bad request (status code 400)
     * or not authorized (status code 401)
     * or Forbidden (status code 403)
     * or Unprocessable request (status code 422)
     * or Internal Server Error (status code 500)
     */
    @PostMapping("/aanvraagwerkzoekende/{oin}")
    public InlineResponse200 createAanvraagWerkzoekende(@Valid @RequestBody WerkzoekendeProfielMatchesRequestGemeente aanvraagWerkzoekende,
                                                        @PathVariable String oin) {
        log.info("Verzoek voor werkzoekende van OIN: " + oin);
        if (getOinUser().equals(oin)) {
            InlineResponse200 result = service.makeRequestMatchesVum(aanvraagWerkzoekende, oin);
            log.info("Succesvolle verzoek voor werkzoekende van OIN: " + oin + " Verzoek heeft gekregen vraagID: " + result.getVraagID());
            return result;
        } else {
            log.info("Mislukte verzoek voor werkzoekende van OIN: " + getOinUser() + "Er is geprobeerd een verzoek in te dienen voor OIN:" + oin);
            throw new WrongOinException();
        }
    }


    /**
     * Utility method to extract OIN from the jwt token given by the user.
     *
     * @return OIN of the user or empty string if no OIN given.
     */
    private String getOinUser() {
        KeycloakAuthenticationToken authentication = (KeycloakAuthenticationToken)
                SecurityContextHolder.getContext().getAuthentication();

        Principal principal = (Principal) authentication.getPrincipal();
        String oinUser = "";

        if (principal instanceof KeycloakPrincipal) {
            KeycloakPrincipal kPrincipal = (KeycloakPrincipal) principal;
            Map<String, Object> customClaims = kPrincipal.getKeycloakSecurityContext().getToken().getOtherClaims();
            if (customClaims.containsKey("oin")) {
                oinUser = String.valueOf(customClaims.get("oin"));
            }
        }
        return oinUser;
    }

}
