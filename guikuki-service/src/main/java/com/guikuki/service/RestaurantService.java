package com.guikuki.service;

import com.guikuki.persistence.exception.RestaurantNotFoundException;
import com.guikuki.service.dto.RestaurantDetailDTO;
import com.guikuki.service.dto.RestaurantListItemDTO;
import com.guikuki.service.dto.RestaurantListDTO;

import java.util.Locale;

/**
 * Service to manage Restaurants.
 * Created by antoniosilvadelpozo on 26/03/14.
 */
public interface RestaurantService {

    /**
     * Return all Restaurants List Items.
     * @param locale
     * @return RestaurantListDTO: List of restaurants.
     */
    public RestaurantListDTO findAllRestaurants(Locale locale);

    /**
     * Returns a specific restaurant's details given its id.
     * @param id: id of the restaurant.
     * @param locale
     * @return RestaurantDetailDTO
     * @throws RestaurantNotFoundException
     */
    public RestaurantDetailDTO findRestaurantById(String id, Locale locale) throws RestaurantNotFoundException;

}
