package com.av.assignment.service;

import com.av.assignment.model.AirportResponseList;
import com.av.assignment.model.Fare;
import com.av.assignment.model.Location;

public interface AirportService {
	
	public AirportResponseList getAirports(Long pageNumber,Long size,String term,String sortBy);
	
	public Location getAirportByCode(String code);
	
	public Fare getAirFare(String source, String destination);

}
