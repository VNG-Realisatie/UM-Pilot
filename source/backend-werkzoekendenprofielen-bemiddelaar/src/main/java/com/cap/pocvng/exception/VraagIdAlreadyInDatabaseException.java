package com.cap.pocvng.exception;

public class VraagIdAlreadyInDatabaseException extends Exception {
    public VraagIdAlreadyInDatabaseException() {
        super("VraagID is already in database.");
    }
}
