package com.avklm.rest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.avklm.error.AirportCustomException;
import com.avklm.model.AirportResponseList;
import com.avklm.model.Location;
import com.avklm.model.metrics.RestApiMetric;
import com.avklm.service.AirportFareService;
import com.avklm.service.AirportService;


@RestController
@RequestMapping("/")
public class AirportControllerImpl implements AirportController{

	@Autowired
	private AirportService airportService;

	@Autowired
	private AirportFareService airportFareService;

	@RequestMapping(method = GET, value = "/search/code")
	public List<Location> populateOriginDest(@RequestParam("term") String term,
			@RequestParam(value = "lang", defaultValue = "en") String lang) throws AirportCustomException {

		return airportFareService.getCodes(term);
	}
	
	@GetMapping("/airports")
	public AirportResponseList getAirports(@RequestParam(value = "lang", defaultValue = "en") String lang,
			@RequestParam(value ="page",defaultValue="1") Long page,
			@RequestParam(value = "size",defaultValue = "10") Long size,
			@RequestParam(value ="term",defaultValue="") String term,
			@RequestParam(value ="sort",defaultValue="code") String sort) throws AirportCustomException {

		return airportService.getAirports(page,size,term,sort);
	}

	@RequestMapping(method = GET, value = "/rest/metrics")
	public List<RestApiMetric>  getMetricsData() throws AirportCustomException{

		return airportService.metricsData();
	}
}