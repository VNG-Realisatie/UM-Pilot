package com.cap.pocvng.config;

import com.cap.pocvng.exception.VacatureNotFoundException;
import com.cap.pocvng.exception.WrongOinException;
import com.cap.pocvng.util.Error;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    ImmutablePair<String, String> ongeldigeAanroep = ImmutablePair.of("400.01", "Ongeldige aanroep");
    ImmutablePair<String, String> onvolledigeAanroep = ImmutablePair.of("400.02", "Onvolledige aanroep");

    @ResponseBody
    @ExceptionHandler(MissingRequestHeaderException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error handleMissingRequestHeaderException(MissingRequestHeaderException exception) {
        return new Error(onvolledigeAanroep.getLeft(), onvolledigeAanroep.getRight(), exception.getLocalizedMessage());
    }

    @ResponseBody
    @ExceptionHandler(WrongOinException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Error handleWrongOinException(WrongOinException exception) {
        return new Error("403.01", "Geen authorizatie", exception.getLocalizedMessage()); //TODO: confirm code
    }

    @ResponseBody
    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error handleNumberFormatException(NumberFormatException exception) {
        return new Error(ongeldigeAanroep.getLeft(), ongeldigeAanroep.getRight(), exception.getLocalizedMessage());
    }


    @ResponseBody
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        return new Error(ongeldigeAanroep.getLeft(), ongeldigeAanroep.getRight(), exception.getLocalizedMessage());
    }


    @ResponseBody
    @ExceptionHandler(VacatureNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error handleNoSuchElementFoundException(VacatureNotFoundException exception) {
        return new Error(ongeldigeAanroep.getLeft(), ongeldigeAanroep.getRight(), exception.getReason());
    }

    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error handleConstraintViolationException(ConstraintViolationException exception) {
        return new Error(ongeldigeAanroep.getLeft(), ongeldigeAanroep.getRight(), exception.getLocalizedMessage());
    }


    /**
     * Handles exceptions whenever an entity is not valid.
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        Error response = new Error(ongeldigeAanroep.getLeft(), ongeldigeAanroep.getRight(), errors.toString());

        return super.handleExceptionInternal(ex, response, headers, status, request);
    }

    /**
     * Handles exceptions whenever incoming JSON is not able to be read.
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        Error response = new Error(onvolledigeAanroep.getLeft(), onvolledigeAanroep.getRight(), ex.getLocalizedMessage());

        return super.handleExceptionInternal(ex, response, headers, status, request);

    }
}
