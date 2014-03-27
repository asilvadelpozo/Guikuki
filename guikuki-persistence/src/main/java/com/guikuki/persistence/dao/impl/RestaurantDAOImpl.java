/**
 * 
 */
package com.guikuki.persistence.dao.impl;

import java.util.List;

import com.guikuki.persistence.dao.RestaurantDAO;
import com.guikuki.persistence.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * {@inheritDoc}
 *
 */
@Repository
public class RestaurantDAOImpl implements RestaurantDAO {

    @Autowired
    MongoOperations mongoOperations;

	/**
	 * {@inheritDoc}
	 *
	 */
	public List<Restaurant> findAllRestaurants() {
        List<Restaurant> restaurants = mongoOperations.findAll(Restaurant.class);
        return restaurants;
	}

	/**
	 * {@inheritDoc}
	 *
	 */
	public Restaurant findRestaurantById(String id) {
        Query searchUserQuery = new Query(Criteria.where("id").is(id));
        Restaurant restaurant = mongoOperations.findOne(searchUserQuery, Restaurant.class);
        return restaurant;
	}

}
