package com.cap.pocvng.controller;

import com.cap.pocvng.entity.Vacature;
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
@Tag(name = "vacatures")

@Slf4j
public class GemeenteController {

    private final GemeenteService service;

    @Autowired
    public GemeenteController(GemeenteService service) {
        this.service = service;
    }

    /**
     * POST /vacature/lijst/{oin} : Endpoint to create several Vacature from a list.
     *
     * @param vacatures list of Vacature
     * @param oin       identifier of municipality
     * @return OK (status code 200)
     * or Bad request (status code 400)
     * or not authorized (status code 401)
     * or Forbidden (status code 403)
     * or Internal Server Error (status code 500)
     */
    @Operation(summary = "Post vacatures", description = "Post vacatures to a given OIN", tags = {})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Vacature.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "401", description = "not authenticated", content = @Content(schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "403", description = "Geen authorizatie", content = @Content(schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = Error.class)))})
    @PostMapping(
            value = "vacature/lijst/{oin}",
            produces = {"application/json"}
    )
    public List<Vacature> createVacatureList(@Valid @RequestBody List<Vacature> vacatures, @PathVariable String oin) {
        log.info("Upload van vacatures door OIN: " + oin);
        String oinUser = getOinUser();
        if (oinUser.equals(oin)) {
            log.info("Succesvolle upload van vacatures door OIN: " + oinUser);
            return service.saveAll(vacatures, oin);
        } else {
            log.info("Mislukte upload van vacatures door OIN: " + oinUser + "Er is geprobeerd te uploaden voor OIN:" + oin);
            throw new WrongOinException();
        }
    }

    /**
     * GET /vacature/lijst/{oin} : Endpoint to retrieve Vacature from the DB.
     *
     * @param oin identifier of municipality
     * @return OK (status code 200)
     * or Bad request (status code 400)
     * or not authorized (status code 401)
     * or Forbidden (status code 403)
     * or Internal Server Error (status code 500)
     */
    @Operation(summary = "Get vacatures", description = "Get vacatures from DB", tags = {})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Vacature.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "401", description = "not authenticated", content = @Content(schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "403", description = "Geen authorizatie", content = @Content(schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = Error.class)))})
    @GetMapping(value = "vacature/lijst/{oin}")
    public List<Vacature> getAll(@PathVariable String oin) {
        String oinUser = getOinUser();
        if (oinUser.equals(oin)) {
            return service.findAll(oin);
        } else {
            log.info("Mislukte GET van vacatures door OIN: " + oinUser + "Er is geprobeerd te GET voor OIN:" + oin);
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
