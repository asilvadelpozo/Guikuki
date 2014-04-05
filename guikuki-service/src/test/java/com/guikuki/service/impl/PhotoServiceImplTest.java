package com.guikuki.service.impl;

import com.guikuki.persistence.dao.PhotoDAO;
import com.guikuki.persistence.exception.PhotoNotFoundException;
import com.guikuki.persistence.model.Photo;
import com.guikuki.service.PhotoService;
import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

/**
 * Created by antoniosilvadelpozo on 26/03/14.
 */
@ContextConfiguration(locations = {"classpath:/service-context-test.xml", "classpath:persistence-context-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class PhotoServiceImplTest {

    @Mock
    private PhotoDAO photoDAO;

    @InjectMocks
    @Autowired
    private PhotoService photoService;

    @Rule
    public ExpectedException photoNotFoundException = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }


    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void should_return_test_photo() throws Exception {
        Photo mockPhoto = createTestPhoto();
        when(photoDAO.findPhotoByFileName("bar_tomate.jpg")).thenReturn(mockPhoto);

        Photo actualPhoto = photoService.findPhotoByFileName("bar_tomate.jpg");
        Photo expectedPhoto = createTestPhoto();

        assertEquals(actualPhoto, expectedPhoto);
    }

    @Test
    public void should_throw_not_found_exception_if_file_name_does_not_exists() throws PhotoNotFoundException {
        String nonExistentFileName = "nonExistentFileName";
        when(photoDAO.findPhotoByFileName(nonExistentFileName)).thenThrow(new PhotoNotFoundException("Photo with fileName: " + nonExistentFileName + " not found."));

        photoNotFoundException.expect(PhotoNotFoundException.class);
        photoNotFoundException.expectMessage("Photo with fileName: " + nonExistentFileName + " not found.");

        photoService.findPhotoByFileName(nonExistentFileName);
    }

    /**
     * Creates a Restaurant instance for testing.
     * @return Restaurant.
     */
    private Photo createTestPhoto() throws IOException {
        FileInputStream fileInputStreamBarTomate = new FileInputStream("src/test/resources/Pictures/bar_tomate.jpg");
        byte[] bytesBarTomate = IOUtils.toByteArray(fileInputStreamBarTomate);
        return new Photo(bytesBarTomate, "bar_tomate.jpg", "image/jpeg");
    }

}
