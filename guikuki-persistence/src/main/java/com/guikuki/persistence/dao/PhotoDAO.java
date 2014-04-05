package com.guikuki.persistence.dao;

import com.guikuki.persistence.exception.PhotoNotFoundException;
import com.guikuki.persistence.model.Photo;

/**
 * DAO for Photos.
 * Created by antoniosilvadelpozo on 24/03/14.
 */
public interface PhotoDAO {

    /**
     * Return a specific Photo given its file name.
     * @param fileName: name of the Photo.
     * @return Photo
     * @throws PhotoNotFoundException
     */
    public abstract Photo findPhotoByFileName(String fileName) throws PhotoNotFoundException;

}
