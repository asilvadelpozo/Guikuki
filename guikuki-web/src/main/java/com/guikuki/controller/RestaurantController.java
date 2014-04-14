package com.guikuki.controller;

import com.guikuki.persistence.exception.RestaurantNotFoundException;
import com.guikuki.service.RestaurantService;
import com.guikuki.service.dto.RestaurantDTO;
import com.guikuki.service.dto.RestaurantsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;

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
    public ModelAndView findAllRestaurants(Locale locale) {
        ModelAndView model = new ModelAndView("restaurants");
        RestaurantsDTO restaurantsDTO = restaurantService.findAllRestaurants(locale);
        model.addObject("restaurants", restaurantsDTO);
        return model;
    }

    @RequestMapping(value = "/restaurants/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView findRestaurantById(@PathVariable String id, Locale locale) throws RestaurantNotFoundException {
        ModelAndView model = new ModelAndView("restaurantDetail");
        RestaurantDTO restaurantDTO = restaurantService.findRestaurantById(id, locale);
        model.addObject("restaurant", restaurantDTO);
        return model;
    }

}
