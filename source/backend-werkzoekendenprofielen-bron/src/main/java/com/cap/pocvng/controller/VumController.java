package com.cap.pocvng.controller;

import com.cap.pocvng.dto.InlineResponse200;
import com.cap.pocvng.dto.WerkzoekendeMatchingProfielen;
import com.cap.pocvng.dto.WerkzoekendeProfielMatchesRequest;
import com.cap.pocvng.entity.Werkzoekende;
import com.cap.pocvng.exception.WerkzoekendeNotFoundException;
import com.cap.pocvng.service.ElkService;
import com.cap.pocvng.service.VumService;
import com.cap.pocvng.util.Error;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.Optional;

@Validated
@Hidden
@Controller
@Slf4j
public class VumController {

    private final VumService vumService;

    private final ElkService elkService;

    @Autowired
    public VumController(VumService vumService, ElkService elkService) {
        this.vumService = vumService;
        this.elkService = elkService;
    }

    /**
     * GET /werkzoekendeProfielen/{idWerkzoekende} : Vraag detailprofielen op bij opgegeven idWerkzoekende
     * Vraag detailprofielen op bij een opgegeven idWerkzoekende
     *
     * @param idWerkzoekende    (required)
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
    @Operation(summary = "Vraag detailprofielen op bij opgegeven idWerkzoekende", description = "Vraag detailprofielen op bij een opgegeven idWerkzoekende", tags = {})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Werkzoekende.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "401", description = "not authorized"),
            @ApiResponse(responseCode = "429", description = "Too Many Requests, limiet is overschreden voor deze uitvraag", content = @Content(schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "503", description = "Service Unavailable", content = @Content(schema = @Schema(implementation = Error.class)))})
    @GetMapping(
            value = "/werkzoekendeProfielen/{idWerkzoekende}",
            produces = {"application/json"}
    )
    public ResponseEntity<Werkzoekende> getWerkzoekendeProfiel(@Size(max = 200) @Parameter(required = true) @PathVariable("idWerkzoekende") String idWerkzoekende,
                                                               @Parameter(required = true) @RequestHeader(value = "X-VUM-berichtVersie", required = true) String xVUMBerichtVersie,
                                                               @Parameter(required = true) @RequestHeader(value = "X-VUM-fromParty", required = true) String xVUMFromParty,
                                                               @Parameter(required = true) @RequestHeader(value = "X-VUM-toParty", required = true) String xVUMToParty,
                                                               @Parameter(required = true) @RequestHeader(value = "X-VUM-SUWIparty", required = true) Boolean xVUMSUWIparty,
                                                               @Parameter() @RequestHeader(value = "X-VUM-viaParty", required = false) String xVUMViaParty) {
        log.info("Aanvraag detailprofiel van ID: " + idWerkzoekende + " door OIN: " + xVUMFromParty + " aan OIN:" + xVUMToParty);
        Optional<Werkzoekende> werkzoekendeOptional = vumService.findById(idWerkzoekende, xVUMToParty);
        if (werkzoekendeOptional.isPresent()) {
            log.info("Succesvolle aanvraag detailprofiel van ID: " + idWerkzoekende + " door OIN: " + xVUMFromParty + " aan OIN:" + xVUMToParty);
            return new ResponseEntity<>(werkzoekendeOptional.get(), HttpStatus.OK);
        } else {
            log.info("Mislukte aanvraag detailprofiel van ID: " + idWerkzoekende + " door OIN: " + xVUMFromParty + " aan OIN:" + xVUMToParty);
            throw new WerkzoekendeNotFoundException("ID niet gevonden");
        }
    }


    /**
     * POST /werkzoekendeProfielen/matches : Zoekopdracht voor MatchingProfielen Werkzoekende
     * Zoekopdracht om matches voor Werkzoekende MatchingProfielen op te vragen
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
    @Operation(summary = "Zoekopdracht voor MatchingProfielen Werkzoekende", description = "Zoekopdracht om matches voor Werkzoekende MatchingProfielen op te vragen", tags = {})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = InlineResponse200.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "401", description = "not authorized"),
            @ApiResponse(responseCode = "429", description = "Too Many Requests", content = @Content(schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "503", description = "Service Unavailable", content = @Content(schema = @Schema(implementation = Error.class)))})
    @PostMapping(
            value = "/werkzoekendeProfielen/matches",
            produces = {"application/json"},
            consumes = {"application/json"}
    )
    public ResponseEntity<InlineResponse200> matchesWerkzoekendeProfielen(@Parameter(required = true) @RequestHeader(value = "X-VUM-berichtVersie", required = true) String xVUMBerichtVersie,
                                                                          @Parameter(required = true) @RequestHeader(value = "X-VUM-fromParty", required = true) String xVUMFromParty,
                                                                          @Parameter(required = true) @RequestHeader(value = "X-VUM-toParty", required = true) String xVUMToParty,
                                                                          @Parameter(required = true) @RequestHeader(value = "X-VUM-SUWIparty", required = true) Boolean xVUMSUWIparty,
                                                                          @Parameter(required = true) @Valid @RequestBody(required = true) WerkzoekendeProfielMatchesRequest matchesRequest,
                                                                          @Parameter() @RequestHeader(value = "X-VUM-viaParty", required = false) String xVUMViaParty) {

        log.info("Aanvraag voor matchingprofielen werkzoekenden door OIN: " + xVUMFromParty + " aan OIN:" + xVUMToParty);
        elkService.handleRequest(matchesRequest, xVUMToParty, xVUMFromParty);
        ImmutablePair<Boolean, WerkzoekendeMatchingProfielen> matches = vumService.match(matchesRequest, xVUMToParty);
        log.info("Succesvolle aanvraag voor matchingprofielen werkzoekenden door OIN: " + xVUMFromParty + " aan OIN:" + xVUMToParty);
        return new ResponseEntity<>(new InlineResponse200(matches.getLeft(), matches.getRight()), HttpStatus.OK);
    }

}
