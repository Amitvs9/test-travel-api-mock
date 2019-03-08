package com.avklm.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.avklm.error.AirportCustomException;
import com.avklm.model.FareDetails;
import com.avklm.model.Location;

public interface AirportFareService {
	
	public CompletableFuture<FareDetails> getFareDetails(String origin,String destination,String currency) throws AirportCustomException;
	public List<Location> getCodes(String code) throws AirportCustomException;
	public CompletableFuture<Location> getOrgDestDetails(String code)throws AirportCustomException;
		
}
