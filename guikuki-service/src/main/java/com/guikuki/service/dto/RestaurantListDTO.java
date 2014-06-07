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
public class RestaurantListDTO {

    protected List<RestaurantListItemDTO> restaurantList;

    public RestaurantListDTO(){}

    public RestaurantListDTO(List<RestaurantListItemDTO> restaurantList) {
        this.restaurantList = restaurantList;
    }

    public RestaurantListDTO(Restaurants restaurants, Locale locale) {
        this.restaurantList = new ArrayList<RestaurantListItemDTO>();
        for(Restaurant restaurant: restaurants.getRestaurantList()) {
            this.restaurantList.add(new RestaurantListItemDTO(restaurant, locale));
        }
    }

    public List<RestaurantListItemDTO> getRestaurantList() {
        return restaurantList;
    }

    @XmlElement(name = "restaurant")
    public void setRestaurantList(List<RestaurantListItemDTO> restaurantList) {
        this.restaurantList = restaurantList;
    }
}
