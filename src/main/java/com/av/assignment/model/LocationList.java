package com.av.assignment.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class LocationList {
	
	private List<Location> location;
	 
    public LocationList() {
    	location = new ArrayList<>();
    }
 

}
