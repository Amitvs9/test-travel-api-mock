package com.av.assignment.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import com.av.assignment.model.Fare;
import com.av.assignment.model.Location;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class AirportServiceImpl implements AirportService {
	
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
	public List<Object> getAllAirports() {
		ResponseEntity<Object[]> response = oAuth2RestTemplate.getForEntity(airportsUrl, Object[].class);
		return Arrays.asList(response.getBody());
	}

	@Override
	public Location getAirportByCode(String code) {
		Map<String, Object> params = new HashMap<>();
		params.put("code", code);
		ResponseEntity<Location> response = oAuth2RestTemplate.getForEntity(searchUrl, Location.class,params);
		return response.getBody();
	}

	@Override
	public Fare getAirFare(String source, String destination) {
		Map<String, Object> params = new HashMap<>();
		params.put("origin_code", source);
		params.put("destination_code", destination);
		ResponseEntity<Fare> response = oAuth2RestTemplate.getForEntity(fareUrl, Fare.class,params);
		return response.getBody();
	}

}
