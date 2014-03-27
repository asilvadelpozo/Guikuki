package com.guikuki.service;

import com.guikuki.persistence.model.Restaurant;

import java.util.List;

/**
 * Service to manage Restaurants.
 * Created by antoniosilvadelpozo on 26/03/14.
 */
public interface RestaurantService {

    /**
     * Return all restaurants.
     * @return ArrayList of restaurants.
     */
    public List<Restaurant> findAllRestaurants();

    /**
     * Returns an specific restaurant given its id.
     * @param id: id of the restaurant.
     * @return Restaurant
     */
    public Restaurant findRestaurantById(String id);

}
