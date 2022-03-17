package com.cap.pocvng.controller;

import com.cap.pocvng.dto.VacatureMatchesCallbackRequest;
import com.cap.pocvng.service.VumService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    /**
     * POST /callback : callback methode
     * Callback methode om matching vacature binnen te krijgen voor Vraag ID.
     *
     * @param xVUMBerichtVersie (required)
     * @param xVUMToParty       (required)
     * @param xVUMVraagID       (required)
     * @param callbackRequest   (required)
     * @param xVUMViaParty      (optional)
     * @return OK (status code 200)
     * or Fout bij uitvoeren van zoekvraag (status code 400)
     * or not authorized (status code 401)
     */
    @ApiOperation(value = "callback methode", nickname = "callback", notes = "Callback methode om matching vacature binnen te krijgen voor Vraag ID.", tags = {})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Fout bij uitvoeren van zoekvraag"),
            @ApiResponse(code = 401, message = "not authorized")})
    @PostMapping(
            value = "/callback",
            consumes = {"application/json"}
    )
    public ResponseEntity<VacatureMatchesCallbackRequest> callback(@ApiParam(value = "", required = true) @RequestHeader(value = "X-VUM-berichtVersie", required = true) String xVUMBerichtVersie,
                                                                   @ApiParam(value = "", required = true) @RequestHeader(value = "X-VUM-toParty", required = true) String xVUMToParty,
                                                                   @ApiParam(value = "", required = true) @RequestHeader(value = "X-VUM-vraagID", required = true) String xVUMVraagID,
                                                                   @ApiParam(value = "", required = true) @Valid @RequestBody VacatureMatchesCallbackRequest callbackRequest,
                                                                   @ApiParam(value = "") @RequestHeader(value = "X-VUM-viaParty", required = false) String xVUMViaParty) {
        log.info("Callback ontvangen voor OIN: " + xVUMToParty + " voor vraagID: " + xVUMVraagID);
        service.handleCallback(callbackRequest);
        log.info("Succesvolle afhandeling callback voor OIN: " + xVUMToParty + " voor vraagID: " + xVUMVraagID);
        return new ResponseEntity<>(HttpStatus.OK);

    }

}
