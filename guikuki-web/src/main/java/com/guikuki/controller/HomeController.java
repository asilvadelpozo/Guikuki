package com.guikuki.controller;

import com.guikuki.service.RestaurantService;
import com.guikuki.service.dto.RestaurantListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;

/**
 * Controller for Home page.
 * Created by antoniosilvadelpozo on 01/04/14.
 */
@Controller
public class HomeController {

    @Autowired
    private RestaurantService restaurantService;

    @RequestMapping(value = {"/","/home"}, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView showHomePage(Locale locale) {
        ModelAndView model = new ModelAndView("home");
        RestaurantListDTO restaurantsDTO = restaurantService.findAllRestaurants(locale);
        model.addObject("restaurants", restaurantsDTO);
        return model;
    }

}
