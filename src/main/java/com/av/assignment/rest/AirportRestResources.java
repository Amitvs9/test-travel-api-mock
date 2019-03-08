package com.av.assignment.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.av.assignment.model.Fare;
import com.av.assignment.model.Location;
import com.av.assignment.model.LocationList;
import com.av.assignment.service.AirportService;

@RestController
@RequestMapping("/travel")
public class AirportRestResources {

	@Autowired
	private AirportService airportService;
	
	@GetMapping("/airports")
	public @ResponseBody LocationList getAllAirports() {

		return airportService.getAllAirports();
	}

	@GetMapping("/search/{code}")
	public Location getAirportByCode(@PathVariable("code") String code ) {
		return airportService.getAirportByCode(code);
	}

	@GetMapping("/fares/{source}/{destination}")
	public Fare getAirFare(@PathVariable("source") String source, @PathVariable("destination") String destination) {

		return airportService.getAirFare(source,destination);
	}
}
