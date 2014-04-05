package com.guikuki.service;

import com.guikuki.persistence.exception.PhotoNotFoundException;
import com.guikuki.persistence.model.Photo;

/**
 * Service to manage Photos.
 * Created by antoniosilvadelpozo on 26/03/14.
 */
public interface PhotoService {

    /**
     * Return a specific Photo given its file name.
     * @param fileName: name of the Photo.
     * @return Photo
     * @throws PhotoNotFoundException
     */
    public abstract Photo findPhotoByFileName(String fileName) throws PhotoNotFoundException;

}