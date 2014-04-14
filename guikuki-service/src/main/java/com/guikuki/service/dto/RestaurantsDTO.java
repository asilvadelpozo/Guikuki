package com.guikuki.service.dto;

import com.guikuki.persistence.model.Restaurant;
import com.guikuki.persistence.model.Restaurants;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by antoniosilvadelpozo on 06/04/14.
 */
@XmlRootElement(name="restaurants")
public class RestaurantsDTO {

    protected List<RestaurantDTO> restaurantList;

    public RestaurantsDTO(){}

    public RestaurantsDTO(List<RestaurantDTO> restaurantList) {
        this.restaurantList = restaurantList;
    }

    public RestaurantsDTO(Restaurants restaurants, Locale locale) {
        this.restaurantList = new ArrayList<RestaurantDTO>();
        for(Restaurant restaurant: restaurants.getRestaurantList()) {
            this.restaurantList.add(new RestaurantDTO(restaurant, locale));
        }
    }

    public List<RestaurantDTO> getRestaurantList() {
        return restaurantList;
    }

    @XmlElement(name = "restaurant")
    public void setRestaurantList(List<RestaurantDTO> restaurantList) {
        this.restaurantList = restaurantList;
    }
}
