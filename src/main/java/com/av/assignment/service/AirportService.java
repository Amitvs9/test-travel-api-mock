package com.av.assignment.service;

import java.util.List;
import java.util.concurrent.Callable;

import com.av.assignment.model.Fare;
import com.av.assignment.model.Location;
import com.av.assignment.model.LocationList;

public interface AirportService {
	
	public LocationList getAllAirports();
	
	public Location getAirportByCode(String code);
	
	public Fare getAirFare(String source, String destination);

}
