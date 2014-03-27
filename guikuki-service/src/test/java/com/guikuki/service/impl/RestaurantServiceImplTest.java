package com.guikuki.service.impl;

import com.guikuki.persistence.dao.RestaurantDAO;
import com.guikuki.persistence.model.Picture;
import com.guikuki.persistence.model.Pictures;
import com.guikuki.persistence.model.Restaurant;
import com.guikuki.service.RestaurantService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * Created by antoniosilvadelpozo on 26/03/14.
 */
@ContextConfiguration(locations = {"classpath:/service-context-test.xml", "classpath:persistence-context-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class RestaurantServiceImplTest {

    @Mock
    private RestaurantDAO restaurantDAO;

    @InjectMocks
    @Autowired
    private RestaurantService restaurantService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void should_return_test_restaurant() throws Exception {
        Restaurant mockRestaurant = createTestRestaurant("id", "name", "description", "filename");
        when(restaurantDAO.findRestaurantById("id")).thenReturn(mockRestaurant);

        Restaurant actualRestaurant = restaurantService.findRestaurantById("id");
        Restaurant expectedRestaurant = createTestRestaurant("id", "name", "description", "filename");

        assertEquals(actualRestaurant, expectedRestaurant);
    }

    @Test
    public void should_return_all_restaurants() throws Exception {
        List<Restaurant> mockRestaurantList = createTestRestaurants();
        when(restaurantDAO.findAllRestaurants()).thenReturn(mockRestaurantList);

        List<Restaurant> actualRestaurantList = restaurantService.findAllRestaurants();
        List<Restaurant> expectedRestaurantList = createTestRestaurants();

        assertThat(actualRestaurantList, containsInAnyOrder(expectedRestaurantList.toArray(new Restaurant[expectedRestaurantList.size()])));
    }

    @Test
    public void should_return_null_if_irestaurant_id_does_not_exists() throws Exception {
        String nonExistentRestaurantId = "nonExistentRestaurantId";

        when(restaurantDAO.findRestaurantById(nonExistentRestaurantId)).thenReturn(null);

        Restaurant nonExistentRestaurant = restaurantService.findRestaurantById(nonExistentRestaurantId);
        assertNull(nonExistentRestaurant);
    }

    /**
     * Creates a Restaurant instance for testing.
     * @return Restaurant.
     */
    private static Restaurant createTestRestaurant(String testId, String testName, String testDescription, String testFileName) {
        Picture testPicture = new Picture(testFileName);
        Pictures testPictures = new Pictures(testPicture);
        return new Restaurant(testId, testName, testDescription, testPictures);
    }

    /**
     * Creates a List of Restaurants instances for testing.
     * @return List<Restaurant>
     */
    private List<Restaurant> createTestRestaurants() {
        List<Restaurant> testRestaurants = new ArrayList<Restaurant>();
        testRestaurants.add(createTestRestaurant("testId1", "testName1", "testDescription1", "testFileName1"));
        testRestaurants.add(createTestRestaurant("testId2", "testName2", "testDescription2", "testFileName2"));
        return testRestaurants;
    }
}
