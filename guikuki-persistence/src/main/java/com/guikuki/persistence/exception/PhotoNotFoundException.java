package com.guikuki.persistence.exception;

/**
 * Exception throw if Photo not found.
 * Created by antoniosilvadelpozo on 05/04/14.
 */
public class PhotoNotFoundException extends Exception {

    /**
     * Public constructor.
     * @param message: message to be shown.
     */
    public PhotoNotFoundException(String message) {
        super(message);
    }

}
