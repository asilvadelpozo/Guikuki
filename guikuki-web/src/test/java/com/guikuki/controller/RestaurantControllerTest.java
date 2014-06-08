package com.guikuki.controller;

import com.guikuki.persistence.exception.RestaurantNotFoundException;
import com.guikuki.persistence.model.Picture;
import com.guikuki.persistence.model.Pictures;
import com.guikuki.service.RestaurantService;
import com.guikuki.service.dto.RestaurantDetailDTO;
import com.guikuki.service.dto.RestaurantListItemDTO;
import com.guikuki.service.dto.RestaurantListDTO;
import com.guikuki.util.UtilTests;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by antoniosilvadelpozo on 06/04/14.
 */
@ContextConfiguration(locations = {"classpath:web-context-test.xml", "classpath:service-context-test.xml", "classpath:persistence-context-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class RestaurantControllerTest {

    private static final Locale ES = new Locale("es", "ES");

    @Mock
    private RestaurantService restaurantService;

    @Autowired
    private RestaurantController restaurantController;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc restaurantControllerMockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        RestaurantController unwrappedRestaurantController = (RestaurantController) UtilTests.unwrapProxy(restaurantController);
        ReflectionTestUtils.setField(unwrappedRestaurantController, "restaurantService", restaurantService);
        restaurantControllerMockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void find_all_restaurants_should_return_html_view_if_no_extension_defined() throws Exception {
        RestaurantListDTO mockRestaurantList = createTestRestaurantsDTO(ES);
        when(restaurantService.findAllRestaurants(ES)).thenReturn(mockRestaurantList);

        RestaurantListDTO expectedRestaurants = createTestRestaurantsDTO(ES);
        List<RestaurantListItemDTO> expectedRestaurantList = expectedRestaurants.getRestaurantList();

        restaurantControllerMockMvc.perform(get("/restaurants").locale(ES))
                .andExpect(status().isOk())
                .andExpect(view().name("restaurants"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/template/layout.jsp"))
                .andExpect(model().attribute("restaurants", hasProperty("restaurantList")))
                .andExpect(model().attribute("restaurants", hasProperty("restaurantList", hasSize(2))))
                .andExpect(model().attribute("restaurants", hasProperty("restaurantList", containsInAnyOrder(expectedRestaurantList.toArray(new RestaurantListItemDTO[expectedRestaurantList.size()])))));
    }

    @Test
    public void find_all_restaurants_should_return_json_view_if_json_extension_defined() throws Exception {
        RestaurantListDTO mockRestaurantList = createTestRestaurantsDTO(ES);
        when(restaurantService.findAllRestaurants(ES)).thenReturn(mockRestaurantList);

        restaurantControllerMockMvc.perform(get("/restaurants.json").locale(ES))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.restaurants.restaurantList", hasSize(2)))
                .andExpect(jsonPath("$.restaurants.restaurantList[0].id", is("testId1")))
                .andExpect(jsonPath("$.restaurants.restaurantList[0].name", is("testName1")))
                .andExpect(jsonPath("$.restaurants.restaurantList[0].description", is("testDescription1es")))
                .andExpect(jsonPath("$.restaurants.restaurantList[1].id", is("testId2")))
                .andExpect(jsonPath("$.restaurants.restaurantList[1].name", is("testName2")))
                .andExpect(jsonPath("$.restaurants.restaurantList[1].description", is("testDescription2es")));
    }

    @Test
    public void find_all_restaurants_should_return_xml_view_if_xml_extension_defined() throws Exception {
        RestaurantListDTO mockRestaurantList = createTestRestaurantsDTO(ES);
        when(restaurantService.findAllRestaurants(ES)).thenReturn(mockRestaurantList);

        String expectedXML = createExpectedXMLStringForFindAllRestaurants();

        restaurantControllerMockMvc.perform(get("/restaurants.xml").locale(ES))
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
                            "<description>testDescription1es</description>" +
                            "<pictures>"+
                                "<main>" +
                                    "<filename>testFileName1</filename>" +
                                "</main>" +
                            "</pictures>" +
                        "</restaurant>" +
                        "<restaurant>" +
                            "<id>testId2</id>" +
                            "<name>testName2</name>" +
                            "<description>testDescription2es</description>" +
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
        List<String> categories = new ArrayList<String>();
        categories.add("category1");
        categories.add("category2");
        RestaurantDetailDTO mockRestaurant = createTestRestaurantDetailDTO("id", "name", "description", categories, "zone", "address", "telephone", "filename");
        when(restaurantService.findRestaurantById("id", ES)).thenReturn(mockRestaurant);

        RestaurantDetailDTO expectedRestaurant = createTestRestaurantDetailDTO("id", "name", "description", categories, "zone", "address", "telephone", "filename");

        restaurantControllerMockMvc.perform(get("/restaurants/{id}", "id").locale(ES))
            .andExpect(status().isOk())
            .andExpect(view().name("restaurantDetail"))
            .andExpect(forwardedUrl("/WEB-INF/jsp/template/layout.jsp"))
            .andExpect(model().attribute("restaurant", is(expectedRestaurant)));
    }

    @Test
    public void find_restaurant_by_id_should_return_json_view_if_json_extension_defined() throws Exception {
        List<String> categories = new ArrayList<String>();
        categories.add("category1");
        categories.add("category2");
        RestaurantDetailDTO mockRestaurant = createTestRestaurantDetailDTO("id", "name", "description", categories, "zone", "address", "telephone", "filename");
        when(restaurantService.findRestaurantById("id", ES)).thenReturn(mockRestaurant);

        restaurantControllerMockMvc.perform(get("/restaurants/{id}.json", "id").locale(ES))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.restaurant.id", is("id")))
                .andExpect(jsonPath("$.restaurant.name", is("name")))
                .andExpect(jsonPath("$.restaurant.description", is("description")))
                .andExpect(jsonPath("$.restaurant.categories[0]", is("category1")))
                .andExpect(jsonPath("$.restaurant.categories[1]", is("category2")))
                .andExpect(jsonPath("$.restaurant.zone", is("zone")))
                .andExpect(jsonPath("$.restaurant.address", is("address")))
                .andExpect(jsonPath("$.restaurant.telephone", is("telephone")));
    }

    @Test
    public void find_restaurant_by_id_should_return_xml_view_if_xml_extension_defined() throws Exception {
        List<String> categories = new ArrayList<String>();
        categories.add("category1");
        categories.add("category2");
        RestaurantDetailDTO mockRestaurant = createTestRestaurantDetailDTO("id", "name", "description", categories, "zone", "address", "telephone", "filename");
        when(restaurantService.findRestaurantById("id", ES)).thenReturn(mockRestaurant);

        String expectedXML = createExpectedXMLStringForFindRestaurantById();

        restaurantControllerMockMvc.perform(get("/restaurants/{id}.xml", "id").locale(ES))
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
                        "<categories>" +
                            "<category>category1</category>" +
                            "<category>category2</category>" +
                        "</categories>" +
                        "<zone>zone</zone>" +
                        "<address>address</address>" +
                        "<telephone>telephone</telephone>" +
                        "<pictures>"+
                            "<main>" +
                                "<filename>filename</filename>" +
                            "</main>" +
                        "</pictures>" +
                    "</restaurant>";
    }

    @Test
    public void find_restaurant_by_id_should_return_404_if_restaurant_id_not_exists() throws Exception {
        String nonExistentRestaurantId = "nonExistentRestaurantId";
        when(restaurantService.findRestaurantById(nonExistentRestaurantId, ES)).thenThrow(new RestaurantNotFoundException("Restaurant with id: " + nonExistentRestaurantId + " not found."));

        restaurantControllerMockMvc.perform(get("/restaurants/{id}", nonExistentRestaurantId).locale(ES))
                .andExpect(status().isNotFound());
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
    private RestaurantListDTO createTestRestaurantsDTO(Locale locale) {
        List<RestaurantListItemDTO> testRestaurantsDTOList = new ArrayList<RestaurantListItemDTO>();
        String testDescription1 = "testDescription1" + locale.getLanguage().toString();
        String testDescription2 = "testDescription2" + locale.getLanguage().toString();
        testRestaurantsDTOList.add(createTestRestaurantListItemDTO("testId1", "testName1", testDescription1, "testFileName1"));
        testRestaurantsDTOList.add(createTestRestaurantListItemDTO("testId2", "testName2", testDescription2, "testFileName2"));
        return new RestaurantListDTO(testRestaurantsDTOList);
    }

}
