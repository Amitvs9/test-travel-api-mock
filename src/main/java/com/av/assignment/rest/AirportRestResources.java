package com.av.assignment.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.av.assignment.model.AirportResponseList;
import com.av.assignment.model.Fare;
import com.av.assignment.model.Location;
import com.av.assignment.service.AirportService;

@RestController
@RequestMapping("/travel")
public class AirportRestResources {

	@Autowired
	private AirportService airportService;
	
	@GetMapping("/search/{code}")
	public Location getAirportByCode(@PathVariable("code") String code ) {
		return airportService.getAirportByCode(code);
	}
	
	@GetMapping("/airports")
	public AirportResponseList getAirports(@RequestParam(value = "lang", defaultValue = "en") String lang,
			@RequestParam(value ="page",defaultValue="1") Long page,
			@RequestParam(value = "size",defaultValue = "25") Long size,
			@RequestParam(value ="term",defaultValue="") String term,
			@RequestParam(value ="sort",defaultValue="code") String sort) {

		return airportService.getAirports(page,size,term,sort);
	}

	@GetMapping("/fares/{source}/{destination}")
	public Fare getAirFare(@PathVariable("source") String source, @PathVariable("destination") String destination) {

		return airportService.getAirFare(source,destination);
	}
}
