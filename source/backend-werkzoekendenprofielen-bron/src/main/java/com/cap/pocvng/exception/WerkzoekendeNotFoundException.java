package com.cap.pocvng.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class WerkzoekendeNotFoundException extends ResponseStatusException {

    public WerkzoekendeNotFoundException(String reason) {
        super(HttpStatus.BAD_REQUEST, reason);
    }
}

