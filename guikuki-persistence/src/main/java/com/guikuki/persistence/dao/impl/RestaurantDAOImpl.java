/**
 * 
 */
package com.guikuki.persistence.dao.impl;

import java.util.List;

import com.guikuki.persistence.dao.RestaurantDAO;
import com.guikuki.persistence.exception.RestaurantNotFoundException;
import com.guikuki.persistence.model.Restaurant;
import com.guikuki.persistence.model.Restaurants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * {@inheritDoc}
 */
@Repository
public class RestaurantDAOImpl implements RestaurantDAO {

    @Autowired
    MongoOperations mongoOperations;

	/**
	 * {@inheritDoc}
	 */
	public Restaurants findAllRestaurants() {
        List<Restaurant> restaurantsList = mongoOperations.findAll(Restaurant.class);
        Restaurants restaurants = new Restaurants(restaurantsList);
        return restaurants;
	}

	/**
	 * {@inheritDoc}
	 */
	public Restaurant findRestaurantById(String id) throws RestaurantNotFoundException{
        Query searchUserQuery = new Query(Criteria.where("id").is(id));
        Restaurant restaurant = mongoOperations.findOne(searchUserQuery, Restaurant.class);

        if(restaurant == null) {
            throw new RestaurantNotFoundException("Restaurant with id: " + id + " not found.");
        }

        return restaurant;
	}

}
