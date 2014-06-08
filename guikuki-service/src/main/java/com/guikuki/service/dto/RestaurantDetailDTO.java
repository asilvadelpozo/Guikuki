package com.guikuki.service.dto;

import com.guikuki.persistence.model.Pictures;
import com.guikuki.persistence.model.Restaurant;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;
import java.util.Locale;

/**
 * Data Transfer Object (DTO) to represent a Restaurant's Details from the mongodb collection restaurants
 * @author antoniosilvadelpozo
 *
 */
@XmlRootElement(name = "restaurant")
@XmlType(propOrder = {"id", "name", "description", "categories", "zone", "address", "telephone", "pictures"})
public class RestaurantDetailDTO {

    private static final Locale DEFAULT_LOCALE = new Locale("es", "ES");

    private String id;
    private String name;
    private String description;
    private List<String> categories;
    private String zone;
    private String address;
    private String telephone;
    private Pictures pictures;

    public RestaurantDetailDTO(){}

    public RestaurantDetailDTO(String id, String name, String description,
           List<String> categories, String zone, String address, String telephone, Pictures pictures) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.categories = categories;
        this.zone = zone;
        this.address = address;
        this.telephone = telephone;
        this.pictures = pictures;
    }

    public RestaurantDetailDTO(Restaurant restaurant, Locale locale) {
        this.id = restaurant.getId();
        this.name = restaurant.getName();
        this.description = (restaurant.getDescription().get(locale.getLanguage().toString()) == null) ?
                restaurant.getDescription().get(DEFAULT_LOCALE.getLanguage().toString()) : restaurant.getDescription().get(locale.getLanguage().toString());
        this.categories = restaurant.getCategories();
        this.zone = restaurant.getZone();
        this.address = restaurant.getAddress();
        this.telephone = restaurant.getTelephone();
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

    public List<String> getCategories() {
        return categories;
    }

    @XmlElementWrapper(name="categories")
    @XmlElement(name="category")
    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public String getZone() {
        return zone;
    }

    @XmlElement(name = "zone")
    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getAddress() {
        return address;
    }

    @XmlElement(name = "address")
    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    @XmlElement(name = "telephone")
    public void setTelephone(String telephone) {
        this.telephone = telephone;
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

        RestaurantDetailDTO that = (RestaurantDetailDTO) o;

        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (categories != null ? !categories.equals(that.categories) : that.categories != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (pictures != null ? !pictures.equals(that.pictures) : that.pictures != null) return false;
        if (telephone != null ? !telephone.equals(that.telephone) : that.telephone != null) return false;
        if (zone != null ? !zone.equals(that.zone) : that.zone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (categories != null ? categories.hashCode() : 0);
        result = 31 * result + (zone != null ? zone.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
        result = 31 * result + (pictures != null ? pictures.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", categories=" + categories.toString() +
                ", zone='" + zone + '\'' +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                ", pictures='" + pictures.toString() +
                '}';
    }
}
