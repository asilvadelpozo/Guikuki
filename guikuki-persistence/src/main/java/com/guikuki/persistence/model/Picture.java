/**
 * 
 */
package com.guikuki.persistence.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Model objecto to represent Restaurants from the mongodb collection a single picture
 * @author antoniosilvadelpozo
 *
 */
@XmlRootElement(name = "picture")
public class Picture {

	private String filename;

    public Picture() {}

    public Picture(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
		return filename;
	}

    @XmlElement(name = "filename")
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
