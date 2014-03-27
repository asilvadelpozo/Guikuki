/**
 * 
 */
package com.guikuki.persistence.model;

/**
 * Model objecto to represent Restaurants from the mongodb collection all the pictures of a restaurant
 * @author antoniosilvadelpozo
 *
 */
public class Pictures {

	private Picture main;

    public Pictures(Picture main) {
        this.main = main;
    }

    public Picture getMain() {
		return main;
	}

	public void setMain(Picture main) {
		this.main = main;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pictures pictures = (Pictures) o;

        if (!main.equals(pictures.main)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return main.hashCode();
    }

    @Override
    public String toString() {
        return "Pictures{" +
                "main=" + main.toString() +
                '}';
    }
}
