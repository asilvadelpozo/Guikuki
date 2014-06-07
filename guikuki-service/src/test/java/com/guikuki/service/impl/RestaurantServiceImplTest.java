package com.guikuki.service.impl;

import com.guikuki.persistence.dao.RestaurantDAO;
import com.guikuki.persistence.exception.RestaurantNotFoundException;
import com.guikuki.persistence.model.Picture;
import com.guikuki.persistence.model.Pictures;
import com.guikuki.persistence.model.Restaurant;
import com.guikuki.persistence.model.Restaurants;
import com.guikuki.service.RestaurantService;
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
        Restaurant mockRestaurant = createMockRestaurant("id", "name", description, "filename");
        when(restaurantDAO.findRestaurantById("id")).thenReturn(mockRestaurant);

        RestaurantListItemDTO actualRestaurant = restaurantService.findRestaurantById("id", ES);
        RestaurantListItemDTO expectedRestaurant = createTestRestaurantDTO("id", "name", "descriptionEs", "filename");

        assertEquals(actualRestaurant, expectedRestaurant);
    }

    @Test
    public void should_return_restaurant_according_to_locale() throws Exception {
        HashMap<String, String> description = new HashMap<String, String>();
        description.put("es", "descriptionEs");
        description.put("en", "descriptionEn");
        Restaurant mockRestaurant = createMockRestaurant("id", "name", description, "filename");
        when(restaurantDAO.findRestaurantById("id")).thenReturn(mockRestaurant);

        RestaurantListItemDTO restaurantEs = restaurantService.findRestaurantById("id", ES);
        RestaurantListItemDTO expectedRestaurantEs = createTestRestaurantDTO("id", "name", "descriptionEs", "filename");

        RestaurantListItemDTO restaurantEn = restaurantService.findRestaurantById("id", EN);
        RestaurantListItemDTO expectedRestaurantEn = createTestRestaurantDTO("id", "name", "descriptionEn", "filename");

        assertEquals(restaurantEs, expectedRestaurantEs);
        assertEquals(restaurantEn, expectedRestaurantEn);
    }

    @Test
    public void should_return_default_restaurant_if_locale_does_not_exists() throws Exception {
        HashMap<String, String> description = new HashMap<String, String>();
        description.put("es", "descriptionEs");
        description.put("en", "descriptionEn");
        Restaurant mockRestaurant = createMockRestaurant("id", "name", description, "filename");
        when(restaurantDAO.findRestaurantById("id")).thenReturn(mockRestaurant);

        RestaurantListItemDTO actualRestaurant = restaurantService.findRestaurantById("id", DE);
        RestaurantListItemDTO expectedRestaurant = createTestRestaurantDTO("id", "name", "descriptionEs", "filename");

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
     * Creates a RestaurantDTO instance for testing.
     * @return RestaurantDTO.
     */
    private RestaurantListItemDTO createTestRestaurantDTO(String testId, String testName, String testDescription, String testFileName) {
        Picture testPicture = new Picture(testFileName);
        Pictures testPictures = new Pictures(testPicture);
        return new RestaurantListItemDTO(testId, testName, testDescription, testPictures);
    }

    /**
     * Creates a List of RestaurantsDTO instances for testing.
     * @return List<Restaurant>
     */
    private RestaurantListDTO createTestRestaurants(Locale locale) {
        List<RestaurantListItemDTO> testRestaurantsDTOList = new ArrayList<RestaurantListItemDTO>();
        String testDescription1 = "testDescription1" + locale.getLanguage().toString();
        String testDescription2 = "testDescription2" + locale.getLanguage().toString();
        testRestaurantsDTOList.add(createTestRestaurantDTO("testId1", "testName1", testDescription1, "testFileName1"));
        testRestaurantsDTOList.add(createTestRestaurantDTO("testId2", "testName2", testDescription2, "testFileName2"));
        return new RestaurantListDTO(testRestaurantsDTOList);
    }

    /**
     * Creates a Restaurant instance for testing.
     * @return Restaurant.
     */
    private static Restaurant createMockRestaurant(String testId, String testName, HashMap<String, String> testDescription, String testFileName) {
        Picture testPicture = new Picture(testFileName);
        Pictures testPictures = new Pictures(testPicture);
        return new Restaurant(testId, testName, testDescription, testPictures);
    }

    /**
     * Creates a List of Restaurants instances for testing.
     * @return List<Restaurant>
     */
    private Restaurants createMockRestaurants() {
        List<Restaurant> testRestaurantsList = new ArrayList<Restaurant>();
        HashMap<String, String> testDescription1 = new HashMap<String, String>();
        testDescription1.put("es", "testDescription1es");
        testDescription1.put("en", "testDescription1en");
        HashMap<String, String> testDescription2 = new HashMap<String, String>();
        testDescription2.put("es", "testDescription2es");
        testDescription2.put("en", "testDescription2en");
        testRestaurantsList.add(createMockRestaurant("testId1", "testName1", testDescription1, "testFileName1"));
        testRestaurantsList.add(createMockRestaurant("testId2", "testName2", testDescription2, "testFileName2"));
        Restaurants testRestaurants = new Restaurants(testRestaurantsList);
        return testRestaurants;
    }
}
