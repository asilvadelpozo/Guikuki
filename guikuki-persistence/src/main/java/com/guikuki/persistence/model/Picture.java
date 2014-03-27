/**
 * 
 */
package com.guikuki.persistence.model;

/**
 * Model objecto to represent Restaurants from the mongodb collection a single picture
 * @author antoniosilvadelpozo
 *
 */
public class Picture {
	
	private String filename;

    public Picture(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
		return filename;
	}
	
	public void setFilename(String filename) {
		this.filename = filename;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Picture picture = (Picture) o;

        if (!filename.equals(picture.filename)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return filename.hashCode();
    }

    @Override
    public String toString() {
        return "Picture{" +
                "filename='" + filename + '\'' +
                '}';
    }
}
