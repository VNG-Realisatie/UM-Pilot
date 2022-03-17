package com.cap.pocvng.controller;

import com.cap.pocvng.dto.InlineResponse200;
import com.cap.pocvng.dto.VacatureMatchesRequestGemeente;
import com.cap.pocvng.entity.AanvraagVacature;
import com.cap.pocvng.entity.Vacature;
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
     * GET /aanvraagvacature/lijst/{oin} : Endpoint to retrieve all outstanding aanvragen for the given OIN.
     *
     * @param oin identifier of municipality
     * @return OK (status code 200)
     * or Bad request (status code 400)
     * or not authorized (status code 401)
     * or Forbidden (status code 403)
     * or Internal Server Error (status code 500)
     */
    @GetMapping("/aanvraagvacature/lijst/{oin}")
    public List<AanvraagVacature> allAanvraagVacatures(@PathVariable String oin) {
        log.info("Verzoek voor alle uitstaande vacatures aanvragen van OIN: " + oin);
        if (getOinUser().equals(oin)) {
            List<AanvraagVacature> result = service.findAll(oin);
            log.info("Succesvolle verzoek voor alle uitstaande vacatures aanvragen van OIN: " + oin);
            return result;
        } else {
            log.info("Mislukte verzoek voor alle uitstaande vacatures door OIN: " + getOinUser() + "Er is geprobeerd te uploaden voor OIN:" + oin);
            throw new WrongOinException();
        }
    }

    /**
     * GET /aanvraagvacature/{oin}/{vraagId} : Endpoint to retrieve aanvraagvacature for the given OIN with vraag ID.
     *
     * @param oin     identifier of municipality
     * @param vraagId identifier of aanvraagVacature.
     * @return OK (status code 200)
     * or Bad request (status code 400)
     * or not authorized (status code 401)
     * or Forbidden (status code 403)
     * or Unprocessable request (status code 422)
     * or Internal Server Error (status code 500)
     */
    @GetMapping("/aanvraagvacature/{oin}/{vraagId}")
    public AanvraagVacature getAanvraagVacature(@PathVariable String oin, @PathVariable String vraagId) {
        log.info("Verzoek voor vacature met vraagID: " + vraagId + " van OIN: " + oin);
        if (getOinUser().equals(oin)) {
            AanvraagVacature result = service.findByVraagIdAndOin(vraagId, oin);
            log.info("Succesvolle verzoek voor vacature met vraagID: " + vraagId + " van OIN: " + oin);
            return result;
        } else {
            log.info("Mislukte verzoek voor vacature met vraagID: " + vraagId + " van OIN: " + oin);
            throw new WrongOinException();
        }
    }

    /**
     * GET /aanvraagvacature/{oin}/{vumId} : Endpoint to retrieve detailed vacature for given vumID.
     *
     * @param oin     identifier of municipality
     * @param vraagId identifier of the AanvraagVacature
     * @param vumId   identifier of vacature
     * @return OK (status code 200)
     * or Bad request (status code 400)
     * or not authorized (status code 401)
     * or Forbidden (status code 403)
     * or Unprocessable request (status code 422)
     * or Internal Server Error (status code 500)
     */
    @GetMapping("/aanvraagvacature/detail/{oin}/{vraagId}/{vumId}")
    public Vacature getDetailVacature(@PathVariable String oin, @PathVariable String vraagId, @PathVariable String vumId) {
        log.info("Verzoek voor detail vacature met vraagID: " + vraagId + " vumID: " + vumId + " van OIN: " + oin);
        if (getOinUser().equals(oin)) {
            Vacature result = service.makeRequestDetailVacature(vraagId, vumId, oin);
            log.info("Succesvolle verzoek voor detail vacature met vraagID: " + vraagId + " vumID: " + vumId + " van OIN: " + oin);
            return result;
        } else {
            log.info("Mislukte verzoek voor detail vacature met vraagID: " + vraagId + " vumID: " + vumId + " van OIN: " + oin);
            throw new WrongOinException();
        }
    }

    /**
     * POST /aanvraagvacature/{oin} : Endpoint to post a request for vacatures matching aanvraagVacature.
     *
     * @param aanvraagVacature AanvraagVacature to match against
     * @param oin              identifier of municipality
     * @return OK (status code 200)
     * or Bad request (status code 400)
     * or not authorized (status code 401)
     * or Forbidden (status code 403)
     * or Unprocessable request (status code 422)
     * or Internal Server Error (status code 500)
     */
    @PostMapping("/aanvraagvacature/{oin}")
    public InlineResponse200 createAanvraagVacature(@Valid @RequestBody VacatureMatchesRequestGemeente aanvraagVacature,
                                                    @PathVariable String oin) {
        log.info("Verzoek voor vacature van OIN: " + oin);
        if (getOinUser().equals(oin)) {
            InlineResponse200 result = service.makeRequestMatchesVum(aanvraagVacature, oin);
            log.info("Succesvolle verzoek voor vacature van OIN: " + oin + " Verzoek heeft gekregen vraagID: " + result.getVraagID());
            return result;
        } else {
            log.info("Mislukte verzoek voor vacature van OIN: " + getOinUser() + "Er is geprobeerd een verzoek in te dienen voor OIN:" + oin);
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
