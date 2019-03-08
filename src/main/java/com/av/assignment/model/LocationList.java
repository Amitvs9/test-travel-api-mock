package com.av.assignment.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

public class LocationList {
	
	private List<Location> location;
	 
    public LocationList() {
    	location = new ArrayList<>();
    }

	public List<Location> getLocation() {
		return location;
	}

	public void setLocation(List<Location> location) {
		this.location = location;
	}
 

}
