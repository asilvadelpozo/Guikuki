/**
 * 
 */
package com.guikuki.service.dto;

import com.guikuki.persistence.model.Pictures;
import com.guikuki.persistence.model.Restaurant;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Locale;

/**
 * Model object to represent Restaurants from the mongodb collection restaurants
 * @author antoniosilvadelpozo
 *
 */
@XmlRootElement(name = "restaurant")
@XmlType(propOrder = {"id", "name", "description", "pictures"})
public class RestaurantDTO {

    private static final Locale DEFAULT_LOCALE = new Locale("es", "ES");

	private String id;
    private String name;
	private String description;
	private Pictures pictures;

    public RestaurantDTO(){}

    public RestaurantDTO(String id, String name, String description, Pictures pictures) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.pictures = pictures;
    }

    public RestaurantDTO(Restaurant restaurant, Locale locale) {
        this.id = restaurant.getId();
        this.name = restaurant.getName();
        this.description = (restaurant.getDescription().get(locale.getLanguage().toString()) == null) ?
                restaurant.getDescription().get(DEFAULT_LOCALE.getLanguage().toString()) : restaurant.getDescription().get(locale.getLanguage().toString());
        this.pictures = restaurant.getPictures();
    }

    public String getId() {
		return id;
	}

    @XmlElement(name = "id")
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

    @XmlElement(name = "name")
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}

    @XmlElement(name = "description")
	public void setDescription(String description) {
		this.description = description;
	}

	public Pictures getPictures() {
		return pictures;
	}

    @XmlElement(name = "pictures")
	public void setPictures(Pictures pictures) {
		this.pictures = pictures;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RestaurantDTO that = (RestaurantDTO) o;

        if (!description.equals(that.description)) return false;
        if (!id.equals(that.id)) return false;
        if (!name.equals(that.name)) return false;
        if (!pictures.equals(that.pictures)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + pictures.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", pictures=" + pictures.toString() +
                '}';
    }
}
