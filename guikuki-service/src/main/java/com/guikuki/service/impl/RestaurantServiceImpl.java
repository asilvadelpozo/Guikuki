package com.guikuki.service.impl;

import com.guikuki.persistence.dao.RestaurantDAO;
import com.guikuki.persistence.exception.RestaurantNotFoundException;
import com.guikuki.service.RestaurantService;
import com.guikuki.service.dto.RestaurantDTO;
import com.guikuki.service.dto.RestaurantsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;

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
    public RestaurantsDTO findAllRestaurants(Locale locale) {
        return new RestaurantsDTO(restaurantDAO.findAllRestaurants(), locale);
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public RestaurantDTO findRestaurantById(String id, Locale locale) throws RestaurantNotFoundException {
        return new RestaurantDTO(restaurantDAO.findRestaurantById(id), locale);
    }


}
