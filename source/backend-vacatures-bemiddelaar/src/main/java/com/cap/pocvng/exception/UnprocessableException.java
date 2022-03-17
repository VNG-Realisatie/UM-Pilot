package com.cap.pocvng.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class UnprocessableException extends ResponseStatusException {

    public UnprocessableException(String reason) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, reason);
    }
}
