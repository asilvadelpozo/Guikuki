/**
 * 
 */
package com.guikuki.persistence.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Model object to represent Restaurants from the mongodb collection restaurants
 * @author antoniosilvadelpozo
 *
 */
@Document(collection = "restaurants")
@XmlRootElement(name = "restaurant")
@XmlType(propOrder = {"id", "name", "description", "pictures"})
public class Restaurant {
	
	@Id
	private String id;
    private String name;
	private String description;
	private Pictures pictures;

    public Restaurant(){}

    public Restaurant(String id, String name, String description, Pictures pictures) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.pictures = pictures;
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

        Restaurant that = (Restaurant) o;

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
