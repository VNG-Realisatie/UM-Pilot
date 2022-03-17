package com.cap.pocvng.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Error
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Error {

    public Error(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @NotNull
    @Size(max = 10)
    private String code;

    @NotNull
    @Size(max = 500)
    private String message;

    @Size(max = 2000)
    private String details;
}
