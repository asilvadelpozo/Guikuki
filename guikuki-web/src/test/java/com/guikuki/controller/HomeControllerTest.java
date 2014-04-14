package com.guikuki.controller;

import com.guikuki.service.RestaurantService;
import com.guikuki.service.dto.RestaurantsDTO;
import com.guikuki.util.UtilTests;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Locale;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by antoniosilvadelpozo on 10/04/14.
 */
@ContextConfiguration(locations = {"classpath:web-context-test.xml", "classpath:service-context-test.xml", "classpath:persistence-context-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class HomeControllerTest {

    private static final Locale ES = new Locale("es", "ES");

    @Mock
    private RestaurantService restaurantService;

    @Autowired
    private HomeController homeController;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc homeControllerMockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        HomeController unwrappedHomeController = (HomeController) UtilTests.unwrapProxy(homeController);
        ReflectionTestUtils.setField(unwrappedHomeController, "restaurantService", restaurantService);
        homeControllerMockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void show_home_page_should_return_html_view() throws Exception {
        RestaurantsDTO mockRestaurantList = mock(RestaurantsDTO.class);
        when(restaurantService.findAllRestaurants(ES)).thenReturn(mockRestaurantList);

        homeControllerMockMvc.perform(get("/home"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/template/layout.jsp"));
    }
}
