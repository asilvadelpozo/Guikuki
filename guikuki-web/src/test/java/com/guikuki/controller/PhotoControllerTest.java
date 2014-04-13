package com.guikuki.controller;

import com.guikuki.persistence.exception.PhotoNotFoundException;
import com.guikuki.persistence.model.Photo;
import com.guikuki.service.PhotoService;
import com.guikuki.util.UtilTests;
import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.FileInputStream;
import java.io.IOException;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by antoniosilvadelpozo on 10/04/14.
 */
@ContextConfiguration(locations = {"classpath:web-context-test.xml", "classpath:service-context-test.xml", "classpath:persistence-context-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class PhotoControllerTest {

    @Mock
    private PhotoService photoService;

    @Autowired
    private PhotoController photoController;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc photoControllerMockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        PhotoController unwrappedPhotoController = (PhotoController) UtilTests.unwrapProxy(photoController);
        ReflectionTestUtils.setField(unwrappedPhotoController, "photoService", photoService);
        photoControllerMockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void find_photo_content_by_file_name_should_return_jpeg_file_if_file_name_exists() throws Exception {
        Photo mockPhoto = createTestPhoto();
        when(photoService.findPhotoByFileName("bar_tomate.jpg")).thenReturn(mockPhoto);

        Photo expectedPhoto = createTestPhoto();

        photoControllerMockMvc.perform(get("/photo/{filename}/content", "bar_tomate"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.IMAGE_JPEG))
                .andExpect(content().bytes(expectedPhoto.getContent()));
    }

    @Test
    public void find_photo_content_by_file_name_should_return_404_if_file_name_not_exists() throws Exception {
        String nonExistentFileName = "nonExistentFileName";
        when(photoService.findPhotoByFileName(nonExistentFileName + ".jpg")).thenThrow(new PhotoNotFoundException("Photo with fileName: " + nonExistentFileName + " not found."));

        photoControllerMockMvc.perform(get("/photo/{filename}/content", nonExistentFileName))
                .andExpect(status().isNotFound());
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
