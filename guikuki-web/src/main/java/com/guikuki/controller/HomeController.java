package com.guikuki.controller;

import com.guikuki.persistence.model.Restaurant;
import com.guikuki.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by antoniosilvadelpozo on 01/04/14.
 */
@Controller
public class HomeController {

    @Autowired
    private RestaurantService restaurantService;

    @RequestMapping(value = {"/","/home"}, method = RequestMethod.GET)
    public ModelAndView showHomePage() {
        ModelAndView model = new ModelAndView("home");
        List<Restaurant> restaurantList = restaurantService.findAllRestaurants();
        model.addObject("restaurantList", restaurantList);
        return model;
    }

}
