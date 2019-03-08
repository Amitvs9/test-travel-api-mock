package com.avklm.rest;

import java.util.List;

import com.avklm.error.AirportCustomException;
import com.avklm.model.AirportResponseList;
import com.avklm.model.Location;

public interface AirportController {
	
	public List<Location> populateOriginDest(String term,String lang) throws AirportCustomException;
	public AirportResponseList getAirports(String lang,Long pageNumber,Long size,String term,String sort) throws AirportCustomException ;

}
