package com.guikuki.persistence.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by antoniosilvadelpozo on 06/04/14.
 */
@XmlRootElement(name="restaurants")
public class Restaurants{

    protected List<Restaurant> restaurantList;

    public Restaurants(){}

    public Restaurants(List<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
    }

    public List<Restaurant> getRestaurantList() {
        return restaurantList;
    }

    @XmlElement(name = "restaurant")
    public void setRestaurantList(List<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
    }
}
