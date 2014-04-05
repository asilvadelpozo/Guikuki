package com.guikuki.persistence.dao.impl;

import com.guikuki.persistence.dao.PhotoDAO;
import com.guikuki.persistence.exception.PhotoNotFoundException;
import com.guikuki.persistence.model.Photo;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.apache.commons.io.IOUtils;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static org.junit.Assert.*;

/**
 * Created by antoniosilvadelpozo on 24/03/14.
 */
@ContextConfiguration(locations={"classpath:persistence-context-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class PhotoDAOImplTest {

    @Autowired
    private GridFsOperations gridFSOperations;

    @Autowired
    private PhotoDAO photoDAO;

    @Rule
    public ExpectedException photoNotFoundException = ExpectedException.none();

    /**
     * @throws Exception
     */
    @Before
    public void fillEmbeddedMongoDBWithTestPhotos() throws Exception {
        FileInputStream fileInputStreamBarTomate = new FileInputStream("src/test/resources/Pictures/bar_tomate.jpg");
        gridFSOperations.store(fileInputStreamBarTomate, "bar_tomate.jpg", "image/jpeg");
        FileInputStream fileInputStreamMunoca = new FileInputStream("src/test/resources/Pictures/munoca.jpg");
        gridFSOperations.store(fileInputStreamMunoca, "munoca.jpg", "image/jpeg");
    }

    /**
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_return_test_photo() throws Exception {
        Photo actualPhoto = photoDAO.findPhotoByFileName("bar_tomate.jpg");
        FileInputStream fileInputStreamBarTomate = new FileInputStream("src/test/resources/Pictures/bar_tomate.jpg");
        byte[] bytesBarTomate = IOUtils.toByteArray(fileInputStreamBarTomate);
        Photo expectedPhoto = new Photo(bytesBarTomate, "bar_tomate.jpg", "image/jpeg");
        assertEquals(actualPhoto, expectedPhoto);
    }

    @Test
    public void should_throw_not_found_exception_if_file_name_does_not_exists() throws PhotoNotFoundException {
        String nonExistentFileName = "nonExistentFileName";

        photoNotFoundException.expect(PhotoNotFoundException.class);
        photoNotFoundException.expectMessage("Photo with fileName: " + nonExistentFileName + " not found.");

        photoDAO.findPhotoByFileName(nonExistentFileName);
    }

}
