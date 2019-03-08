package com.avklm.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.avklm.data.AirportDataImpl;
import com.avklm.error.AirportCustomException;
import com.avklm.model.FareDetails;
import com.avklm.model.Location;
import com.avklm.rest.AirportFareController;

@Service
public class AirportFareServiceImpl implements AirportFareService{
	private static Logger log = LoggerFactory.getLogger(AirportFareServiceImpl.class);
	@Autowired
	AirportDataImpl restClient;
	
	@Async("asyncExecutor")
	@Override
	public CompletableFuture<FareDetails> getFareDetails(String origin,String destination,String currency) throws AirportCustomException{
		log.info("getOrgDestDetails getFareData");
		return CompletableFuture.completedFuture(restClient.getFareData(origin, destination, currency));
	}
	


	
	@Override
	@Async("asyncExecutor")
	public CompletableFuture<Location> getOrgDestDetails(String code)throws AirportCustomException{
		log.info("getOrgDestDetails started"+code);
		return CompletableFuture.completedFuture(restClient.getOrgDestDetails(code));
	}
	
	@Override
	public List<Location> getCodes(String code) throws AirportCustomException {
		
	return restClient.getCodes(code).getEmbedded().getLocations().
				stream().
				map(p -> new Location(p.getCode(), p.getName(), p.getDescription()))
		        .collect(Collectors.toList());
	}

		
	
}
