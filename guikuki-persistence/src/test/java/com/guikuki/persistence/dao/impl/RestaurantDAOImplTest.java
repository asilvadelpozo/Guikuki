/**
 * 
 */
package com.guikuki.persistence.dao.impl;

import com.guikuki.persistence.dao.RestaurantDAO;
import com.guikuki.persistence.exception.RestaurantNotFoundException;
import com.guikuki.persistence.model.Picture;
import com.guikuki.persistence.model.Pictures;
import com.guikuki.persistence.model.Restaurant;
import com.guikuki.persistence.model.Restaurants;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author antoniosilvadelpozo
 *
 */
@ContextConfiguration(locations={"classpath:persistence-context-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class RestaurantDAOImplTest {

    @Autowired
    private MongoOperations mongoOperations;

    @Autowired
    private RestaurantDAO restaurantDAO;

    @Rule
    public ExpectedException restaurantNotFoundException = ExpectedException.none();

	/**
	 * @throws Exception
	 */
	@Before
	public void fillEmbeddedMongoDBWithTestRestaurants() throws Exception {
        List<Restaurant> testRestaurants = createTestRestaurants();
        for(Restaurant testRestaurant : testRestaurants) {
            mongoOperations.insert(testRestaurant);
        }
	}

    /**
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

    @Test
    public void should_return_test_restaurant() throws RestaurantNotFoundException{
        Restaurant actualRestaurant = restaurantDAO.findRestaurantById("testId1");
        HashMap<String, String> testDescription1 = new HashMap<String, String>();
        testDescription1.put("es", "testDescription1Es");
        testDescription1.put("en", "testDescription1En");
        Restaurant expectedRestaurant = createTestRestaurant("testId1", "testName1", testDescription1, "testFileName1");
        assertThat(actualRestaurant, equalTo(expectedRestaurant));
    }

	@Test
	public void should_return_all_restaurants() {
        Restaurants actualRestaurants = restaurantDAO.findAllRestaurants();
        List<Restaurant> actualRestaurantList = actualRestaurants.getRestaurantList();
        List<Restaurant> expectedRestaurantList = createTestRestaurants();
        assertThat(actualRestaurantList, containsInAnyOrder(expectedRestaurantList.toArray(new Restaurant[expectedRestaurantList.size()])));
	}

    @Test
    public void should_throw_not_found_exception_if_restaurant_id_does_not_exists() throws RestaurantNotFoundException {
        String nonExistentRestaurantId = "nonExistentRestaurantId";

        restaurantNotFoundException.expect(RestaurantNotFoundException.class);
        restaurantNotFoundException.expectMessage("Restaurant with id: " + nonExistentRestaurantId + " not found.");

        restaurantDAO.findRestaurantById(nonExistentRestaurantId);
    }

    /**
     * Creates a Restaurant instance for testing.
     * @return Restaurant.
     */
    private Restaurant createTestRestaurant(String testId, String testName, HashMap<String, String> testDescription, String testFileName) {
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
        HashMap<String, String> testDescription1 = new HashMap<String, String>();
        testDescription1.put("es", "testDescription1Es");
        testDescription1.put("en", "testDescription1En");
        HashMap<String, String> testDescription2 = new HashMap<String, String>();
        testDescription2.put("es", "testDescription2Es");
        testDescription2.put("en", "testDescription2En");
        testRestaurants.add(createTestRestaurant("testId1", "testName1", testDescription1, "testFileName1"));
        testRestaurants.add(createTestRestaurant("testId2", "testName2", testDescription2, "testFileName2"));
        return testRestaurants;
    }

}
