package com.guikuki.service;

import com.guikuki.persistence.exception.RestaurantNotFoundException;
import com.guikuki.persistence.model.Restaurant;
import com.guikuki.persistence.model.Restaurants;

/**
 * Service to manage Restaurants.
 * Created by antoniosilvadelpozo on 26/03/14.
 */
public interface RestaurantService {

    /**
     * Return all restaurants.
     * @return Restaurants: List of restaurants.
     */
    public Restaurants findAllRestaurants();

    /**
     * Returns an specific restaurant given its id.
     * @param id: id of the restaurant.
     * @return Restaurant
     * @throws RestaurantNotFoundException
     */
    public Restaurant findRestaurantById(String id) throws RestaurantNotFoundException;

}
