package com.guikuki.persistence.model;

import java.util.List;

/**
 * Created by antoniosilvadelpozo on 06/04/14.
 */
public class Restaurants{

    protected List<Restaurant> restaurantList;

    public Restaurants(){}

    public Restaurants(List<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
    }

    public List<Restaurant> getRestaurantList() {
        return restaurantList;
    }

    public void setRestaurantList(List<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
    }
}
