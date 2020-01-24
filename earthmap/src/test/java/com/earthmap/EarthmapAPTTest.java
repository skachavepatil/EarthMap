package com.earthmap;

import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import com.earthmap.controllers.FourSquareAPIController;
import com.earthmap.models.Category;
import com.earthmap.models.FourSquareResponse;
import com.earthmap.models.PreferredPlaces;

@RunWith(SpringRunner.class)
@WebMvcTest
public class EarthmapAPTTest {
    @Autowired
    private MockMvc mockMvc;
    
    @Mock
    RestTemplate restTemplate;
    
    @Before
    public void setUp() {
    	
    	PreferredPlaces preferredPlacesList;
    	preferredPlacesList = Mockito.mock(PreferredPlaces.class);
    }

    @MockBean
    private FourSquareAPIController fsaController;
    private FourSquareResponse fsResponse;

    @Test
    public void getAllCategoriesTest() throws Exception {
        
        Category category = new Category();
        category.setId("1001");
        category.setName("mycategory");
        category.setPluralName("mypluralname");
        category.setShortName("myshortname");

        List<Category> categories = Arrays.asList(category);
        given(fsaController.getCategories()).willReturn(categories);

        this.mockMvc.perform(get("/foursquare/categories"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id': '1001'; 'name': 'mycategory';'pluralName':'mypluralname';'shortName':'myshortname'}]"));
        
    }
    
    @Test
    public void createListTest() throws Exception {
    	
    	PreferredPlaces preferredPlacesList = new PreferredPlaces();
    	
    	preferredPlacesList.setName("mylist");
    	preferredPlacesList.setDescription("mylistdescription");
    	
    	final String uri = "https://api.foursquare.com/v2/lists/add?name='" + preferredPlacesList.getName() +
				   "'&description=" + preferredPlacesList.getDescription() +
				    "&oauth_token=HKGOZGZU3ZFON2RXCLCIFMGY4GIFURLYW5AFNORTGLD1R1S4&v=20190911&_=1568207759591";
    	
    	assertNotNull(uri);
      	
    }
        
}

