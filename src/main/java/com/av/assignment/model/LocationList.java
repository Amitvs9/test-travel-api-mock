package com.av.assignment.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Value;

@Value
public class LocationList {
	
	private List<Location> location;
	 
    public LocationList() {
    	location = new ArrayList<>();
    }
 

}
