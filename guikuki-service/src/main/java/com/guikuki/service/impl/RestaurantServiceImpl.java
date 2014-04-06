package com.guikuki.service.impl;

import com.guikuki.persistence.dao.RestaurantDAO;
import com.guikuki.persistence.exception.RestaurantNotFoundException;
import com.guikuki.persistence.model.Restaurant;
import com.guikuki.persistence.model.Restaurants;
import com.guikuki.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * {@inheritDoc}
 *
 */
@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantDAO restaurantDAO;

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public Restaurants findAllRestaurants() {
        return restaurantDAO.findAllRestaurants();
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public Restaurant findRestaurantById(String id) throws RestaurantNotFoundException {
        return restaurantDAO.findRestaurantById(id);
    }

}
