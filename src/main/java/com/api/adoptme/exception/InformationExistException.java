package com.api.adoptme.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)// sets HTTP response status to 409 Conflict
public class InformationExistException extends RuntimeException {
    /**
     * Constructor for InformationExistException
     *
     * @param message the error message for the exception
     */
    public InformationExistException(String message) {
        super(message);
    }
}
