package com.guikuki.service.impl;

import com.guikuki.persistence.dao.RestaurantDAO;
import com.guikuki.persistence.model.Restaurant;
import com.guikuki.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Restaurant> findAllRestaurants() {
        return restaurantDAO.findAllRestaurants();
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public Restaurant findRestaurantById(String id) {
        return restaurantDAO.findRestaurantById(id);
    }

}
