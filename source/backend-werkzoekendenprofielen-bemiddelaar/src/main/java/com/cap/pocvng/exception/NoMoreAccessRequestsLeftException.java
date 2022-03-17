package com.cap.pocvng.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
public class NoMoreAccessRequestsLeftException extends ResponseStatusException {
    public NoMoreAccessRequestsLeftException() {
        super(HttpStatus.TOO_MANY_REQUESTS, "limiet is overschreden voor deze vraagID");
    }
}
