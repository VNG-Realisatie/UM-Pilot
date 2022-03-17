package com.cap.pocvng.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class WrongOinException extends ResponseStatusException {

    public WrongOinException() {
        super(HttpStatus.FORBIDDEN, "Invalide OIN");
    }
}
