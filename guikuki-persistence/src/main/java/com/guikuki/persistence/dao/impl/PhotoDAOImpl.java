package com.guikuki.persistence.dao.impl;

import com.guikuki.persistence.dao.PhotoDAO;
import com.guikuki.persistence.exception.PhotoNotFoundException;
import com.guikuki.persistence.model.Photo;
import com.mongodb.gridfs.GridFSDBFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Repository;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class PhotoDAOImpl implements PhotoDAO {

    @Autowired
    private GridFsOperations gridFsOperations;

    /**
     * {@inheritDoc}
     */
    @Override
    public Photo findPhotoByFileName(String fileName) throws PhotoNotFoundException {

        Photo photo = null;

        List<GridFSDBFile> gridFSDBFileList = gridFsOperations.find(new Query().addCriteria(Criteria.where("filename").is(fileName)));

        if(gridFSDBFileList == null || gridFSDBFileList.isEmpty()) {
            throw new PhotoNotFoundException("Photo with fileName: " + fileName + " not found.");
        }

        ByteArrayOutputStream byteArrayOutputStream = null;
        for (GridFSDBFile gridFSDBFile : gridFSDBFileList) {
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                gridFSDBFile.writeTo(byteArrayOutputStream);
                photo = new Photo(byteArrayOutputStream.toByteArray(), gridFSDBFile.getFilename(), gridFSDBFile.getContentType());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                if(byteArrayOutputStream != null)
                    try {
                        byteArrayOutputStream.close();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        }
        return photo;
    }
}
