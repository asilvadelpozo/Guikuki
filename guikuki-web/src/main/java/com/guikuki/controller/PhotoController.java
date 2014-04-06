package com.guikuki.controller;

import com.guikuki.persistence.exception.PhotoNotFoundException;
import com.guikuki.persistence.model.Photo;
import com.guikuki.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller for Photo resources.
 * Created by antoniosilvadelpozo on 01/04/14.
 */
@Controller
@RequestMapping(value = "/photo")
public class PhotoController {

    private static final String JPEG_EXTENSION = ".jpg";

    @Autowired
    private PhotoService photoService;

    @RequestMapping(value = "/{filename}/content", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] findPhotoContentByFileName(@PathVariable String filename) throws PhotoNotFoundException {
        Photo photo = photoService.findPhotoByFileName(filename + JPEG_EXTENSION);
        return photo.getContent();
    }

}
