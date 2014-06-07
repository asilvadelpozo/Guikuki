package com.guikuki.service;

import com.guikuki.persistence.exception.RestaurantNotFoundException;
import com.guikuki.service.dto.RestaurantListItemDTO;
import com.guikuki.service.dto.RestaurantListDTO;

import java.util.Locale;

/**
 * Service to manage Restaurants.
 * Created by antoniosilvadelpozo on 26/03/14.
 */
public interface RestaurantService {

    /**
     * Return all restaurants.
     * @param locale
     * @return Restaurants: List of restaurants.
     */
    public RestaurantListDTO findAllRestaurants(Locale locale);

    /**
     * Returns an specific restaurant given its id.
     * @param id: id of the restaurant.
     * @param locale
     * @return Restaurant
     * @throws RestaurantNotFoundException
     */
    public RestaurantListItemDTO findRestaurantById(String id, Locale locale) throws RestaurantNotFoundException;

}
