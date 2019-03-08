package com.av.assignment.service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import com.av.assignment.error.AirportNotFoundException;
import com.av.assignment.error.RestTemplateResponseErrorHandler;
import com.av.assignment.model.AirportResponseList;
import com.av.assignment.model.AirportRespose;
import com.av.assignment.model.Fare;
import com.av.assignment.model.Location;


@Service
public class AirportServiceImpl implements AirportService {
	
	private static final String CODE = "CODE";
	private static final String NAME = "NAME";
	private static final String DESC = "DESC";
	
	@Value("${mock.fareurl:http://localhost:8080/fares/{origin_code}/{destination_code}}")
	private String fareUrl;

	@Value("${mock.searchurl:http://localhost:8080/airports/{code}}")
	private String searchUrl;

	@Value("${mock.airportsurl:http://localhost:8080/airports}")
	private String airportsUrl;

	public String getAirportsUrl() {
		return airportsUrl;
	}

	public String getFareUrl() {
		return fareUrl;
	}

	public String getSearchUrl() {
		return searchUrl;
	}
	
	@Autowired
	OAuth2RestTemplate oAuth2RestTemplate;

	
	@Override
	public AirportResponseList getAirports(Long pageNumber,Long size,String term,String sortBy) {
	
	return populateAirports(getAllAirports(),sortBy); 
	}
	
	private AirportResponseList populateAirports(AirportRespose codes,String sort){
		
		AirportResponseList list = new AirportResponseList();
		list.setPage(codes.getPage());
		
		switch (sort) {
		case CODE:
			list.setLocations(codes.getEmbedded().getLocations().stream().sorted(Comparator.comparing(Location::getCode)).collect(Collectors.toList()));
			break;
		case NAME:
			list.setLocations(codes.getEmbedded().getLocations().stream().sorted(Comparator.comparing(Location::getName)).collect(Collectors.toList()));
			break;
		case DESC:
			list.setLocations(codes.getEmbedded().getLocations().stream().sorted(Comparator.comparing(Location::getDescription)).collect(Collectors.toList()));
			break;
		default:
			list.setLocations(codes.getEmbedded().getLocations().stream().sorted(Comparator.comparing(Location::getCode)).collect(Collectors.toList()));
			break;
		}
		
		return list;
	}
	
	private AirportRespose getAllAirports() {
		ResponseEntity<AirportRespose> response = oAuth2RestTemplate.getForEntity(airportsUrl, AirportRespose.class);
		if(response.getStatusCode()!=HttpStatus.OK) {
			throw new AirportNotFoundException();
		}
		return response.getBody();
	}

	@Override
	public Location getAirportByCode(String code) {
		Map<String, Object> params = new HashMap<>();
		params.put("code", code);
		ResponseEntity<Location> response = oAuth2RestTemplate.getForEntity(searchUrl, Location.class,params);
		if(response.getStatusCode()!=HttpStatus.OK) {
			throw new AirportNotFoundException();
		}
		return response.getBody();
	}

	@Override
	public Fare getAirFare(String source, String destination) {
		Map<String, Object> params = new HashMap<>();
		params.put("origin_code", source);
		params.put("destination_code", destination);
		ResponseEntity<Fare> response = oAuth2RestTemplate.getForEntity(fareUrl, Fare.class,params);
		if(response.getStatusCode()!=HttpStatus.OK) {
			throw new AirportNotFoundException();
		}
		return response.getBody();
	}

}
