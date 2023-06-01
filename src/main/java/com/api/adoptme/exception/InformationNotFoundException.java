package com.api.adoptme.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)// sets HTTP response status to 404 Not Found
public class InformationNotFoundException extends RuntimeException {
    /**
     * Constructor for InformationNotFoundException
     *
     * @param message the error message for the exception
     */
    public InformationNotFoundException(String message) {
        super(message);
    }
}
