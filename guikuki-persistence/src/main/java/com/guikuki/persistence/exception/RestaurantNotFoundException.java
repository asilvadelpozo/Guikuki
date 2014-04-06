package com.guikuki.persistence.exception;

/**
 * Exception throw if Restaurant not found.
 * Created by antoniosilvadelpozo on 05/04/14.
 */
public class RestaurantNotFoundException extends ResourceNotFoundException {

    /**
     * Public constructor.
     * @param message: message to be shown.
     */
    public RestaurantNotFoundException(String message) {
        super(message);
    }

}
