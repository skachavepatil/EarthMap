package com.earthmap.controllers;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.earthmap.models.Category;
import com.earthmap.models.FourSquareResponse;
import com.earthmap.models.PreferredPlaces;
import com.earthmap.models.Venue;

@RestController
@RequestMapping("/foursquare")
public class FourSquareAPIController {

	Logger logger = LogManager.getLogger(FourSquareAPIController.class);
	
	//Get the location
	@GetMapping("/search/{location}")
	public List<Venue> searchByLocation(@PathVariable("location") String location) {
		
		logger.info("Getting the location information.");
		
		final String uri = "https://api.foursquare.com/v2/venues/search?near='"+ location +
						   "'&oauth_token=HKGOZGZU3ZFON2RXCLCIFMGY4GIFURLYW5AFNORTGLD1R1S4&v=20190911&_=1568207759591";

	    RestTemplate restTemplate = new RestTemplate();
	    FourSquareResponse result = restTemplate.getForObject(uri, FourSquareResponse.class);
	    
	    return result.getResponse().getVenues();
	    
	}
	
	//Get categories
	@GetMapping("/categories")
	public List<Category> getCategories() {
		
		logger.info("Getting the categories information.");
		
		final String uri = "https://api.foursquare.com/v2/venues/categories?'" + 
						   "'&oauth_token=HKGOZGZU3ZFON2RXCLCIFMGY4GIFURLYW5AFNORTGLD1R1S4&v=20190911&_=1568207759591";
			
	    RestTemplate restTemplate = new RestTemplate();
	    FourSquareResponse result = restTemplate.getForObject(uri, FourSquareResponse.class);
	 	    
	    return result.getResponse().getCategories();
	 	
	}

	// Create the list
	@PostMapping("/createlist")
	public FourSquareResponse createPlacesList(@RequestBody PreferredPlaces places) throws RestClientException, URISyntaxException {
		
		logger.info("Creating the list.");
		
		final String uri = "https://api.foursquare.com/v2/lists/add?name='"+ places.getName() +
						   "'&description=" + places.getDescription()
						   + "&oauth_token=HKGOZGZU3ZFON2RXCLCIFMGY4GIFURLYW5AFNORTGLD1R1S4&v=20190911&_=1568207759591";
		
	    RestTemplate restTemplate = new RestTemplate();
	    FourSquareResponse result = restTemplate.postForObject(uri, places, FourSquareResponse.class);
	    
	    return result;
		
	}
	
	//Add place under the list
	@PostMapping("/placesunderlist")
	public FourSquareResponse createList( @RequestBody PreferredPlaces places) throws RestClientException, URISyntaxException {
		
		logger.info("Creating the places under list.");
		
		logger.info("places.getId()" + places.getId());
		
		final String uri = "https://api.foursquare.com/v2/lists/" + places.getId() + "/additem?LIST_ID='" + places.getId() +						   
						   "'&venueId=" + places.getVenueId()
						  + "&oauth_token=HKGOZGZU3ZFON2RXCLCIFMGY4GIFURLYW5AFNORTGLD1R1S4&v=20190911&_=1568207759591";
		
	    RestTemplate restTemplate = new RestTemplate();
	    FourSquareResponse result = restTemplate.postForObject(uri, places, FourSquareResponse.class);
	    
	    return result;
		
	}
}
