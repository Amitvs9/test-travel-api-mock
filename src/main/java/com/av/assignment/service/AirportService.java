package com.av.assignment.service;

import java.util.List;
import java.util.concurrent.Callable;

import com.av.assignment.model.Fare;
import com.av.assignment.model.Location;

public interface AirportService {
	
	public List<Object>  getAllAirports();
	
	public Location getAirportByCode(String code);
	
	public Fare getAirFare(String source, String destination);

}
