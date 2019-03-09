package com.avklm.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.avklm.error.AirportCustomException;
import com.avklm.model.FareDetails;

@RestController
@RequestMapping("/")
public class AirportFareControllerImpl implements AirportFareController{

	@Autowired
	private AirportAsynchExecutor airportAsynch;


	@Override
	@GetMapping("fares/{origin}/{destination}")
	public FareDetails calculateFare(@PathVariable("origin") String origin,
			@PathVariable("destination") String destination,
			@RequestParam(value = "currency", defaultValue = "EUR") String currency) throws AirportCustomException {
		
		return airportAsynch.callAsynchFare(origin, destination, currency);
	}
}
