package com.guikuki.persistence.exception;

/**
 * Exception throw if Resource not found.
 * Created by antoniosilvadelpozo on 06/04/14.
 */
public class ResourceNotFoundException extends Exception {

    /**
     * Public constructor.
     * @param message: message to be shown.
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }

}
