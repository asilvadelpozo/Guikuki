/**
 * 
 */
package com.guikuki.persistence.dao;

import java.util.List;

import com.guikuki.persistence.exception.RestaurantNotFoundException;
import com.guikuki.persistence.model.Restaurant;
import com.guikuki.persistence.model.Restaurants;

/**
 * DAO for Restaurants
 * @author antoniosilvadelpozo
 *
 */
public interface RestaurantDAO {
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
