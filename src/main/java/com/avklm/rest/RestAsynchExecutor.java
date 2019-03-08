package com.avklm.rest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.avklm.error.AirportCustomException;
import com.avklm.model.FareDetails;
import com.avklm.model.Location;
import com.avklm.service.AirportFareService;

@Component
public class RestAsynchExecutor {

	@Autowired
	private AirportFareService fareService;
	
	public FareDetails callAsynchFare(String origin,
			String destination,
			String currency) throws AirportCustomException {
		
		FareDetails fareDetails =null;
		CompletableFuture<FareDetails> fareData = fareService.getFareDetails(origin, destination, currency);
		CompletableFuture<Location> originDetails = fareService.getOrgDestDetails(origin);
		CompletableFuture<Location> destinationDetails = fareService.getOrgDestDetails(destination);
		
		CompletableFuture.allOf(fareData,originDetails,destinationDetails);
		
		try {
			fareDetails = fareData.get();
			fareDetails.setOrigin(originDetails.get());
			fareDetails.setDestination(destinationDetails.get());
			
		}catch (InterruptedException | ExecutionException e) {
			throw new AirportCustomException(""+e, e.getLocalizedMessage());
			
		}	
		return fareDetails;
	
	}
}
