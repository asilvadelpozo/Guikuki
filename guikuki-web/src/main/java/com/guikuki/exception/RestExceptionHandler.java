package com.guikuki.exception;

import com.guikuki.persistence.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Handler that map Business Exceptions to HTTP error codes.
 * Created by antoniosilvadelpozo on 06/04/14.
 */
@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleRestaurantNotFoundException(ResourceNotFoundException resourceNotFounException) {
        //TO DO: Add log4j and log the exception and the stack trace
    }

}
