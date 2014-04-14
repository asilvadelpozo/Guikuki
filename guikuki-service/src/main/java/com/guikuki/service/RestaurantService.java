package com.guikuki.service;

import com.guikuki.persistence.exception.RestaurantNotFoundException;
import com.guikuki.service.dto.RestaurantDTO;
import com.guikuki.service.dto.RestaurantsDTO;

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
    public RestaurantsDTO findAllRestaurants(Locale locale);

    /**
     * Returns an specific restaurant given its id.
     * @param id: id of the restaurant.
     * @param locale
     * @return Restaurant
     * @throws RestaurantNotFoundException
     */
    public RestaurantDTO findRestaurantById(String id, Locale locale) throws RestaurantNotFoundException;

}
