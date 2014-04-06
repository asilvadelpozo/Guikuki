package com.guikuki.controller;

import com.guikuki.persistence.exception.RestaurantNotFoundException;
import com.guikuki.persistence.model.Picture;
import com.guikuki.persistence.model.Pictures;
import com.guikuki.persistence.model.Restaurant;
import com.guikuki.persistence.model.Restaurants;
import com.guikuki.service.RestaurantService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by antoniosilvadelpozo on 06/04/14.
 */
@ContextConfiguration(locations = {"classpath:web-context-test.xml", "classpath:service-context-test.xml", "classpath:persistence-context-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class RestaurantControllerTest {

    @Mock
    private RestaurantService restaurantService;

    @InjectMocks
    @Autowired
    private RestaurantController restaurantController;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc restaurantControllerMockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        restaurantControllerMockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void find_all_restaurants_should_return_html_view_if_no_extension_defined() throws Exception {
        Restaurants mockRestaurantList = createTestRestaurants();
        when(restaurantService.findAllRestaurants()).thenReturn(mockRestaurantList);

        Restaurants expectedRestaurants = createTestRestaurants();
        List<Restaurant> expectedRestaurantList = expectedRestaurants.getRestaurantList();

        restaurantControllerMockMvc.perform(get("/restaurants"))
                .andExpect(status().isOk())
                .andExpect(view().name("restaurants"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/template/layout.jsp"))
                .andExpect(model().attribute("restaurants", hasProperty("restaurantList")))
                .andExpect(model().attribute("restaurants", hasProperty("restaurantList", hasSize(2))))
                .andExpect(model().attribute("restaurants", hasProperty("restaurantList", containsInAnyOrder(expectedRestaurantList.toArray(new Restaurant[expectedRestaurantList.size()])))));
    }

    @Test
    public void find_all_restaurants_should_return_json_view_if_json_extension_defined() throws Exception {
        Restaurants mockRestaurantList = createTestRestaurants();
        when(restaurantService.findAllRestaurants()).thenReturn(mockRestaurantList);

        restaurantControllerMockMvc.perform(get("/restaurants.json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.restaurants.restaurantList", hasSize(2)))
                .andExpect(jsonPath("$.restaurants.restaurantList[0].id", is("testId1")))
                .andExpect(jsonPath("$.restaurants.restaurantList[0].name", is("testName1")))
                .andExpect(jsonPath("$.restaurants.restaurantList[0].description", is("testDescription1")))
                .andExpect(jsonPath("$.restaurants.restaurantList[1].id", is("testId2")))
                .andExpect(jsonPath("$.restaurants.restaurantList[1].name", is("testName2")))
                .andExpect(jsonPath("$.restaurants.restaurantList[1].description", is("testDescription2")));
    }

    @Test
    public void find_all_restaurants_should_return_xml_view_if_xml_extension_defined() throws Exception {
        Restaurants mockRestaurantList = createTestRestaurants();
        when(restaurantService.findAllRestaurants()).thenReturn(mockRestaurantList);

        String expectedXML = createExpectedXMLStringForFindAllRestaurants();

        restaurantControllerMockMvc.perform(get("/restaurants.xml"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_XML))
                .andExpect(content().xml(expectedXML));
    }

    private String createExpectedXMLStringForFindAllRestaurants() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
                    "<restaurants>" +
                        "<restaurant>" +
                            "<id>testId1</id>" +
                            "<name>testName1</name>" +
                            "<description>testDescription1</description>" +
                            "<pictures>"+
                                "<main>" +
                                    "<filename>testFileName1</filename>" +
                                "</main>" +
                            "</pictures>" +
                        "</restaurant>" +
                        "<restaurant>" +
                            "<id>testId2</id>" +
                            "<name>testName2</name>" +
                            "<description>testDescription2</description>" +
                            "<pictures>"+
                                "<main>" +
                                    "<filename>testFileName2</filename>" +
                                "</main>" +
                            "</pictures>" +
                        "</restaurant>" +
                    "</restaurants>";
    }

    @Test
    public void find_restaurant_by_id_should_return_html_view_if_no_extension_defined() throws Exception {
        Restaurant mockRestaurant = createTestRestaurant("id", "name", "description", "filename");
        when(restaurantService.findRestaurantById("id")).thenReturn(mockRestaurant);

        Restaurant expectedRestaurant = createTestRestaurant("id", "name", "description", "filename");

        restaurantControllerMockMvc.perform(get("/restaurants/{id}", "id"))
            .andExpect(status().isOk())
            .andExpect(view().name("restaurantDetail"))
            .andExpect(forwardedUrl("/WEB-INF/jsp/template/layout.jsp"))
            .andExpect(model().attribute("restaurant", is(expectedRestaurant)));
    }

    @Test
    public void find_restaurant_by_id_should_return_json_view_if_json_extension_defined() throws Exception {
        Restaurant mockRestaurant = createTestRestaurant("id", "name", "description", "filename");
        when(restaurantService.findRestaurantById("id")).thenReturn(mockRestaurant);

        restaurantControllerMockMvc.perform(get("/restaurants/{id}.json", "id"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.restaurant.id", is("id")))
                .andExpect(jsonPath("$.restaurant.name", is("name")))
                .andExpect(jsonPath("$.restaurant.description", is("description")));
    }

    @Test
    public void find_restaurant_by_id_should_return_xml_view_if_xml_extension_defined() throws Exception {
        Restaurant mockRestaurant = createTestRestaurant("id", "name", "description", "filename");
        when(restaurantService.findRestaurantById("id")).thenReturn(mockRestaurant);

        String expectedXML = createExpectedXMLStringForFindRestaurantById();

        restaurantControllerMockMvc.perform(get("/restaurants/{id}.xml", "id"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_XML))
                .andExpect(content().xml(expectedXML));
    }

    private String createExpectedXMLStringForFindRestaurantById() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
                    "<restaurant>" +
                        "<id>id</id>" +
                        "<name>name</name>" +
                        "<description>description</description>" +
                        "<pictures>"+
                            "<main>" +
                                "<filename>filename</filename>" +
                            "</main>" +
                        "</pictures>" +
                    "</restaurant>";
    }

    @Test
    public void find_restaurant_by_id_should_return_404_if_restaurant_id_non_existent() throws Exception {
        String nonExistentRestaurantId = "nonExistentRestaurantId";
        when(restaurantService.findRestaurantById(nonExistentRestaurantId)).thenThrow(new RestaurantNotFoundException("Restaurant with id: " + nonExistentRestaurantId + " not found."));

        restaurantControllerMockMvc.perform(get("/restaurants/{id}", nonExistentRestaurantId))
                .andExpect(status().isNotFound());
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
    private Restaurants createTestRestaurants() {
        List<Restaurant> testRestaurantsList = new ArrayList<Restaurant>();
        testRestaurantsList.add(createTestRestaurant("testId1", "testName1", "testDescription1", "testFileName1"));
        testRestaurantsList.add(createTestRestaurant("testId2", "testName2", "testDescription2", "testFileName2"));
        Restaurants testRestaurants = new Restaurants(testRestaurantsList);
        return testRestaurants;
    }

}
