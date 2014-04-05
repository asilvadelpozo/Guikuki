package com.guikuki.service.impl;

import com.guikuki.persistence.dao.PhotoDAO;
import com.guikuki.persistence.exception.PhotoNotFoundException;
import com.guikuki.persistence.model.Photo;
import com.guikuki.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * {@inheritDoc}
 *
 */
@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoDAO photoDAO;

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public Photo findPhotoByFileName(String fileName) throws PhotoNotFoundException {
        return photoDAO.findPhotoByFileName(fileName);
    }
}
