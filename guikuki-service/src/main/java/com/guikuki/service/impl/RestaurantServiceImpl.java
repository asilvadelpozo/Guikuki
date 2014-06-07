package com.guikuki.service.impl;

import com.guikuki.persistence.dao.RestaurantDAO;
import com.guikuki.persistence.exception.RestaurantNotFoundException;
import com.guikuki.service.RestaurantService;
import com.guikuki.service.dto.RestaurantListItemDTO;
import com.guikuki.service.dto.RestaurantListDTO;
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
    public RestaurantListDTO findAllRestaurants(Locale locale) {
        return new RestaurantListDTO(restaurantDAO.findAllRestaurants(), locale);
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public RestaurantListItemDTO findRestaurantById(String id, Locale locale) throws RestaurantNotFoundException {
        return new RestaurantListItemDTO(restaurantDAO.findRestaurantById(id), locale);
    }


}
