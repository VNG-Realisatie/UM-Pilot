package com.cap.pocvng.controller;

import com.cap.pocvng.entity.Werkzoekende;
import com.cap.pocvng.exception.WrongOinException;
import com.cap.pocvng.service.GemeenteService;
import com.cap.pocvng.util.Error;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@Validated
@RestController
@Tag(name = "werkzoekendeProfielen")
@Slf4j
public class GemeenteController {

    private final GemeenteService service;

    @Autowired
    public GemeenteController(GemeenteService service) {
        this.service = service;
    }

    /**
     * POST /werkzoekende/lijst/{oin} : Endpoint to create several Werkzoekende from a list.
     *
     * @param werkzoekenden list of Werkzoekende.
     * @param oin           identifier of municipality
     * @return OK (status code 200)
     * or Bad request (status code 400)
     * or not authorized (status code 401)
     * or Forbidden (status code 403)
     * or Internal Server Error (status code 500)
     */


    @Operation(summary = "Post werkzoekenden", description = "Post werkzoekenden to a given OIN", tags = {})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Werkzoekende.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "401", description = "not authenticated", content = @Content(schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "403", description = "Geen authorizatie", content = @Content(schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = Error.class)))})
    @PostMapping(
            value = "werkzoekende/lijst/{oin}",
            produces = {"application/json"}
    )
    public List<Werkzoekende> createWerkzoekendeList(@Valid @RequestBody List<Werkzoekende> werkzoekenden, @PathVariable String oin) {
        log.info("Upload van profielen door OIN: " + oin);
        String oinUser = getOinUser();
        if (oinUser.equals(oin)) {
            log.info("Succesvolle upload van profielen door OIN: " + oinUser);
            return service.saveAll(werkzoekenden, oin);
        } else {
            log.info("Mislukte upload van profielen door OIN: " + oinUser + "Er is geprobeerd te uploaden voor OIN:" + oin);
            throw new WrongOinException();
        }
    }


    /**
     * GET /werkzoekende/lijst/{oin} : Endpoint to retrieve all Werkzoekende from the DB.
     *
     * @param oin identifier of municipality
     * @return OK (status code 200)
     * or Bad request (status code 400)
     * or not authorized (status code 401)
     * or Forbidden (status code 403)
     * or Internal Server Error (status code 500)
     */
    @Operation(summary = "Get werkzoekenden", description = "Get werkzoekenden from DB", tags = {})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Werkzoekende.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "401", description = "not authenticated", content = @Content(schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "403", description = "Geen authorizatie", content = @Content(schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = Error.class)))})

    @GetMapping(
            value = "werkzoekende/lijst/{oin}",
            produces = {"application/json"}
    )
    public List<Werkzoekende> getAll(@PathVariable String oin) {
        String oinUser = getOinUser();
        if (oinUser.equals(oin)) {
            return service.findAll(oin);
        } else {
            log.info("Mislukte GET profielen door OIN: " + oinUser + "Er is geprobeerd GET voor OIN:" + oin);
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
