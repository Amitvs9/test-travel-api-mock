package com.avklm.rest;

import com.avklm.error.AirportCustomException;
import com.avklm.model.FareDetails;

public interface AirportFareController {
	public FareDetails calculateFare(String origin,
			String destination,
			String currency) throws AirportCustomException;
	
}
