package com.cap.pocvng.controller;

import com.cap.pocvng.dto.WerkzoekendeProfielMatchesCallbackRequest;
import com.cap.pocvng.service.VumService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Validated
@Slf4j
public class VumController {

    private final VumService service;

    public VumController(VumService service) {
        this.service = service;
    }

    @ApiOperation(value = "callback methode", nickname = "callback", notes = "Callback methode om matching werkzoekenden binnen te krijgen voor Vraag ID.", tags = {})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Fout bij uitvoeren van zoekvraag"),
            @ApiResponse(responseCode = "401", description = "not authorized")})
    @PostMapping(
            value = "/callback",
            consumes = {"application/json"})
    public ResponseEntity<Void> callback(@Parameter(required = true) @RequestHeader(value = "X-VUM-berichtVersie", required = true) String xVUMBerichtVersie,
                                         @Parameter(required = true) @RequestHeader(value = "X-VUM-toParty", required = true) String xVUMToParty,
                                         @Parameter(required = true) @RequestHeader(value = "X-VUM-vraagID", required = true) String xVUMVraagID,
                                         @Parameter(required = true) @Valid @RequestBody WerkzoekendeProfielMatchesCallbackRequest matchesRequest,
                                         @Parameter() @RequestHeader(value = "X-VUM-viaParty", required = false) String xVUMViaParty) {

        log.info("Callback ontvangen voor OIN: " + xVUMToParty + " voor vraagID: " + xVUMVraagID);
        service.handleCallback(matchesRequest);
        log.info("Succesvolle afhandeling callback voor OIN: " + xVUMToParty + " voor vraagID: " + xVUMVraagID);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

