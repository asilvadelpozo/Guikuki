package com.guikuki.service.impl;

import com.guikuki.persistence.dao.RestaurantDAO;
import com.guikuki.persistence.exception.RestaurantNotFoundException;
import com.guikuki.persistence.model.Picture;
import com.guikuki.persistence.model.Pictures;
import com.guikuki.persistence.model.Restaurant;
import com.guikuki.persistence.model.Restaurants;
import com.guikuki.service.RestaurantService;
import com.guikuki.service.dto.RestaurantDetailDTO;
import com.guikuki.service.dto.RestaurantListItemDTO;
import com.guikuki.service.dto.RestaurantListDTO;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created by antoniosilvadelpozo on 26/03/14.
 */
@ContextConfiguration(locations = {"classpath:service-context-test.xml", "classpath:persistence-context-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class RestaurantServiceImplTest {

    private static final Locale ES = new Locale("es", "ES");
    private static final Locale EN = new Locale("en", "EN");
    private static final Locale DE = new Locale("de", "DE");

    @Mock
    private RestaurantDAO restaurantDAO;

    @InjectMocks
    @Autowired
    private RestaurantService restaurantService;

    @Rule
    public ExpectedException restaurantNotFoundException = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_return_test_restaurant() throws Exception {
        HashMap<String, String> description = new HashMap<String, String>();
        description.put("es", "descriptionEs");
        description.put("en", "descriptionEn");
        List<String> categories = new ArrayList<String>();
        categories.add("category1");
        categories.add("category2");
        Restaurant mockRestaurant = createMockRestaurant("id", "name", description, categories, "zone", "address", "telephone", "filename");
        when(restaurantDAO.findRestaurantById("id")).thenReturn(mockRestaurant);

        RestaurantDetailDTO actualRestaurant = restaurantService.findRestaurantById("id", ES);
        RestaurantDetailDTO expectedRestaurant = createTestRestaurantDetailDTO("id", "name", "descriptionEs", categories, "zone", "address", "telephone", "filename");

        assertEquals(actualRestaurant, expectedRestaurant);
    }

    @Test
    public void should_return_restaurant_according_to_locale() throws Exception {
        HashMap<String, String> description = new HashMap<String, String>();
        description.put("es", "descriptionEs");
        description.put("en", "descriptionEn");
        List<String> categories = new ArrayList<String>();
        categories.add("category1");
        categories.add("category2");
        Restaurant mockRestaurant = createMockRestaurant("id", "name", description, categories, "zone", "address", "telephone", "filename");
        when(restaurantDAO.findRestaurantById("id")).thenReturn(mockRestaurant);

        RestaurantDetailDTO restaurantEs = restaurantService.findRestaurantById("id", ES);
        RestaurantDetailDTO expectedRestaurantEs = createTestRestaurantDetailDTO("id", "name", "descriptionEs", categories, "zone", "address", "telephone", "filename");

        RestaurantDetailDTO restaurantEn = restaurantService.findRestaurantById("id", EN);
        RestaurantDetailDTO expectedRestaurantEn = createTestRestaurantDetailDTO("id", "name", "descriptionEn", categories, "zone", "address", "telephone", "filename");

        assertEquals(restaurantEs, expectedRestaurantEs);
        assertEquals(restaurantEn, expectedRestaurantEn);
    }

    @Test
    public void should_return_default_restaurant_if_locale_does_not_exists() throws Exception {
        HashMap<String, String> description = new HashMap<String, String>();
        description.put("es", "descriptionEs");
        description.put("en", "descriptionEn");
        List<String> categories = new ArrayList<String>();
        categories.add("category1");
        categories.add("category2");
        Restaurant mockRestaurant = createMockRestaurant("id", "name", description, categories, "zone", "address", "telephone", "filename");
        when(restaurantDAO.findRestaurantById("id")).thenReturn(mockRestaurant);

        RestaurantDetailDTO actualRestaurant = restaurantService.findRestaurantById("id", DE);
        RestaurantDetailDTO expectedRestaurant = createTestRestaurantDetailDTO("id", "name", "descriptionEs", categories, "zone", "address", "telephone", "filename");

        assertEquals(actualRestaurant, expectedRestaurant);
    }

    @Test
    public void should_return_all_restaurants() throws Exception {
        Restaurants mockRestaurantList = createMockRestaurants();
        when(restaurantDAO.findAllRestaurants()).thenReturn(mockRestaurantList);

        RestaurantListDTO actualRestaurants = restaurantService.findAllRestaurants(ES);
        List<RestaurantListItemDTO> actualRestaurantList = actualRestaurants.getRestaurantList();
        RestaurantListDTO expectedRestaurants = createTestRestaurants(ES);
        List<RestaurantListItemDTO> expectedRestaurantList = expectedRestaurants.getRestaurantList();

        assertThat(actualRestaurantList, containsInAnyOrder(expectedRestaurantList.toArray(new RestaurantListItemDTO[expectedRestaurantList.size()])));
    }

    @Test
    public void should_return_default_restaurants_if_locale_does_not_exists() throws Exception {
        Restaurants mockRestaurantList = createMockRestaurants();
        when(restaurantDAO.findAllRestaurants()).thenReturn(mockRestaurantList);

        RestaurantListDTO actualRestaurants = restaurantService.findAllRestaurants(DE);
        List<RestaurantListItemDTO> actualRestaurantList = actualRestaurants.getRestaurantList();
        RestaurantListDTO expectedRestaurants = createTestRestaurants(ES);
        List<RestaurantListItemDTO> expectedRestaurantList = expectedRestaurants.getRestaurantList();

        assertThat(actualRestaurantList, containsInAnyOrder(expectedRestaurantList.toArray(new RestaurantListItemDTO[expectedRestaurantList.size()])));
    }

    @Test
    public void should_throw_not_found_exception_if_restaurant_id_does_not_exists() throws RestaurantNotFoundException {
        String nonExistentRestaurantId = "nonExistentRestaurantId";
        when(restaurantDAO.findRestaurantById(nonExistentRestaurantId)).thenThrow(new RestaurantNotFoundException("Restaurant with id: " + nonExistentRestaurantId + " not found."));

        restaurantNotFoundException.expect(RestaurantNotFoundException.class);
        restaurantNotFoundException.expectMessage("Restaurant with id: " + nonExistentRestaurantId + " not found.");

        restaurantService.findRestaurantById(nonExistentRestaurantId, ES);
    }

    /**
     * Creates a RestaurantListItemDTO instance for testing.
     * @return RestaurantListItemDTO.
     */
    private RestaurantListItemDTO createTestRestaurantListItemDTO(String testId, String testName, String testDescription, String testFileName) {
        Picture testPicture = new Picture(testFileName);
        Pictures testPictures = new Pictures(testPicture);
        return new RestaurantListItemDTO(testId, testName, testDescription, testPictures);
    }

    /**
     * Creates a RestaurantDetailDTO instance for testing.
     * @return RestaurantDetailDTO.
     */
    private RestaurantDetailDTO createTestRestaurantDetailDTO(String testId, String testName, String testDescription,
            List<String> testCategories, String testZone, String testAddress, String testTelephone, String testFileName) {
        Picture testPicture = new Picture(testFileName);
        Pictures testPictures = new Pictures(testPicture);
        return new RestaurantDetailDTO(testId, testName, testDescription, testCategories, testZone, testAddress, testTelephone, testPictures);
    }

    /**
     * Creates a List of RestaurantsDTO instances for testing.
     * @return List<Restaurant>
     */
    private RestaurantListDTO createTestRestaurants(Locale locale) {
        List<RestaurantListItemDTO> testRestaurantsDTOList = new ArrayList<RestaurantListItemDTO>();
        String testDescription1 = "testDescription1" + locale.getLanguage().toString();
        String testDescription2 = "testDescription2" + locale.getLanguage().toString();
        testRestaurantsDTOList.add(createTestRestaurantListItemDTO("testId1", "testName1", testDescription1, "testFileName1"));
        testRestaurantsDTOList.add(createTestRestaurantListItemDTO("testId2", "testName2", testDescription2, "testFileName2"));
        return new RestaurantListDTO(testRestaurantsDTOList);
    }

    /**
     * Creates a Restaurant instance for testing.
     * @return Restaurant.
     */
    private static Restaurant createMockRestaurant(String testId, String testName, HashMap<String, String> testDescription,
            List<String> categories, String zone, String address, String telephone, String testFileName) {
        Picture testPicture = new Picture(testFileName);
        Pictures testPictures = new Pictures(testPicture);
        return new Restaurant(testId, testName, testDescription, categories, zone, address, telephone, testPictures);
    }

    /**
     * Creates a List of Restaurants instances for testing.
     * @return Restaurants
     */
    private Restaurants createMockRestaurants() {
        List<Restaurant> testRestaurants = new ArrayList<Restaurant>();
        HashMap<String, String> testDescription1 = new HashMap<String, String>();
        testDescription1.put("es", "testDescription1es");
        testDescription1.put("en", "testDescription1en");
        List<String> testCategories1 = new ArrayList<String>();
        testCategories1.add("testCategory1");
        testCategories1.add("testCategory2");
        HashMap<String, String> testDescription2 = new HashMap<String, String>();
        testDescription2.put("es", "testDescription2es");
        testDescription2.put("en", "testDescription2en");
        List<String> testCategories2 = new ArrayList<String>();
        testCategories2.add("testCategory3");
        testCategories2.add("testCategory4");
        testRestaurants.add(createMockRestaurant("testId1", "testName1", testDescription1, testCategories1,
                "testZone1", "testAddress1", "testTelephone1", "testFileName1"));
        testRestaurants.add(createMockRestaurant("testId2", "testName2", testDescription2, testCategories2,
                "testZone2", "testAddress2", "testTelephone2", "testFileName2"));
        return new Restaurants(testRestaurants);
    }
}
