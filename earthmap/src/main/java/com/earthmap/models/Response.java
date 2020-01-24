package com.earthmap.models;

import java.util.List;

public class Response {

	private List<Venue> venues;
	
	private PreferredPlaces list;
	
	private List<Category> categories;

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public List<Venue> getVenues() {
		return venues;
	}

	public void setVenues(List<Venue> venues) {
		this.venues = venues;
	}

	public PreferredPlaces getList() {
		return list;
	}

	public void setList(PreferredPlaces list) {
		this.list = list;
	}
	
	
}
