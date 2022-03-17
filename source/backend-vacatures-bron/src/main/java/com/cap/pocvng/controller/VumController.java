/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.32).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.cap.pocvng.controller;


import com.cap.pocvng.dto.InlineResponse200;
import com.cap.pocvng.dto.VacatureMatchesRequest;
import com.cap.pocvng.dto.VacatureMatchingProfielen;
import com.cap.pocvng.entity.Vacature;
import com.cap.pocvng.exception.VacatureNotFoundException;
import com.cap.pocvng.service.VumService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


@Validated
@Hidden
@Controller
@Slf4j
public class VumController {

    private final VumService service;

    public VumController(VumService service) {
        this.service = service;
    }

    /**
     * GET /vacatures/{idVacature} : Vraag detailvacatures op bij opgegeven idVacature
     * Vraag detailprofielen op bij een opgegeven idVacature
     *
     * @param idVacature        (required)
     * @param xVUMBerichtVersie (required)
     * @param xVUMFromParty     (required)
     * @param xVUMToParty       (required)
     * @param xVUMSUWIparty     (required)
     * @param xVUMViaParty      (optional)
     * @return OK (status code 200)
     * or Bad request (status code 400)
     * or not authorized (status code 401)
     * or Too Many Requests, limiet is overschreden voor deze uitvraag (status code 429)
     * or Internal Server Error (status code 500)
     * or Service Unavailable (status code 503)
     */
    @Operation(summary = "Vraag volledige vacature op bij opgegeven idVacature", description = "Vraag volledige vacature op bij een opgegeven idVacature", tags = {})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Vacature.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "401", description = "not authorized"),
            @ApiResponse(responseCode = "429", description = "Too Many Requests, limiet is overschreden voor deze uitvraag", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "503", description = "Service Unavailable", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class)))})
    @GetMapping(
            value = "/vacatures/{idVacature}",
            produces = {"application/json"}
    )
    public ResponseEntity<Vacature> getVacature(@Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("idVacature") String idVacature,
                                                @Parameter(in = ParameterIn.HEADER, description = "", required = true, schema = @Schema()) @RequestHeader(value = "X-VUM-berichtVersie", required = true) String xVUMBerichtVersie,
                                                @Parameter(in = ParameterIn.HEADER, description = "", required = true, schema = @Schema()) @RequestHeader(value = "X-VUM-fromParty", required = true) String xVUMFromParty,
                                                @Parameter(in = ParameterIn.HEADER, description = "", required = true, schema = @Schema()) @RequestHeader(value = "X-VUM-toParty", required = true) String xVUMToParty,
                                                @Parameter(in = ParameterIn.HEADER, description = "", required = true, schema = @Schema()) @RequestHeader(value = "X-VUM-SUWIparty", required = true) Boolean xVUMSUWIparty,
                                                @Parameter(in = ParameterIn.HEADER, description = "", schema = @Schema()) @RequestHeader(value = "X-VUM-viaParty", required = false) String xVUMViaParty) {
        log.info("Aanvraag detailvacature van ID: " + idVacature + " door OIN: " + xVUMFromParty + " aan OIN:" + xVUMToParty);
        Optional<Vacature> vacatureOptional = service.findById(idVacature, xVUMToParty);
        if (vacatureOptional.isPresent()) {
            log.info("Succesvolle aanvraag detailprofiel van ID: " + idVacature + " door OIN: " + xVUMFromParty + " aan OIN:" + xVUMToParty);
            return new ResponseEntity<>(vacatureOptional.get(), HttpStatus.OK);
        } else {
            log.info("Mislukte aanvraag detailprofiel van ID: " + idVacature + " door OIN: " + xVUMFromParty + " aan OIN:" + xVUMToParty);
            throw new VacatureNotFoundException("ID niet gevonden");
        }

    }


    /**
     * POST /vacatures/matches : Zoekopdracht voor Matching Vacature
     * Zoekopdracht om matches voor Vacature op te vragen
     *
     * @param xVUMBerichtVersie (required)
     * @param xVUMFromParty     (required)
     * @param xVUMToParty       (required)
     * @param xVUMSUWIparty     (required)
     * @param matchesRequest    (required)
     * @param xVUMViaParty      (optional)
     * @return OK (status code 200)
     * or Bad request (status code 400)
     * or not authorized (status code 401)
     * or Too Many Requests (status code 429)
     * or Internal Server Error (status code 500)
     * or Service Unavailable (status code 503)
     */
    @Operation(summary = "Zoekopdracht voor Vacatures", description = "Zoekopdracht om Vacature matches op te vragen", tags = {})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = InlineResponse200.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "401", description = "not authorized"),
            @ApiResponse(responseCode = "429", description = "Too Many Requests", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "503", description = "Service Unavailable", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class)))})
    @PostMapping(
            value = "/vacatures/matches",
            produces = {"application/json"},
            consumes = {"application/json"}
    )
    public ResponseEntity<InlineResponse200> matchesVacatures(@Parameter(in = ParameterIn.HEADER, description = "", required = true, schema = @Schema()) @RequestHeader(value = "X-VUM-berichtVersie", required = true) String xVUMBerichtVersie,
                                                              @Parameter(in = ParameterIn.HEADER, description = "", required = true, schema = @Schema()) @RequestHeader(value = "X-VUM-fromParty", required = true) String xVUMFromParty,
                                                              @Parameter(in = ParameterIn.HEADER, description = "", required = true, schema = @Schema()) @RequestHeader(value = "X-VUM-toParty", required = true) String xVUMToParty,
                                                              @Parameter(in = ParameterIn.HEADER, description = "", required = true, schema = @Schema()) @RequestHeader(value = "X-VUM-SUWIparty", required = true) Boolean xVUMSUWIparty,
                                                              @Parameter(in = ParameterIn.DEFAULT, description = "", required = true, schema = @Schema()) @Valid @RequestBody VacatureMatchesRequest matchesRequest,
                                                              @Parameter(in = ParameterIn.HEADER, description = "", schema = @Schema()) @RequestHeader(value = "X-VUM-viaParty", required = false) String xVUMViaParty) {

        log.info("Aanvraag voor matching vacatures door OIN: " + xVUMFromParty + " aan OIN:" + xVUMToParty);
        ImmutablePair<Boolean, VacatureMatchingProfielen> matches = service.match(matchesRequest, xVUMToParty, xVUMFromParty);
        log.info("Succesvolle aanvraag voor matchingvacatures door OIN: " + xVUMFromParty + " aan OIN:" + xVUMToParty);
        return new ResponseEntity<>(new InlineResponse200(matches.getLeft(), matches.getRight()), HttpStatus.OK);

    }

}

