package com.guikuki.controller;

import com.guikuki.persistence.exception.RestaurantNotFoundException;
import com.guikuki.persistence.model.Restaurant;
import com.guikuki.persistence.model.Restaurants;
import com.guikuki.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for Restaurant resources.
 * Created by antoniosilvadelpozo on 01/04/14.
 */
@Controller
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @RequestMapping(value = "/restaurants", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView findAllRestaurants() {
        ModelAndView model = new ModelAndView("restaurants");
        Restaurants restaurants = restaurantService.findAllRestaurants();
        model.addObject("restaurants", restaurants);
        return model;
    }

    @RequestMapping(value = "/restaurants/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView findRestaurantById(@PathVariable String id) throws RestaurantNotFoundException {
        ModelAndView model = new ModelAndView("restaurantDetail");
        Restaurant restaurant = restaurantService.findRestaurantById(id);
        model.addObject("restaurant", restaurant);
        return model;
    }

}
