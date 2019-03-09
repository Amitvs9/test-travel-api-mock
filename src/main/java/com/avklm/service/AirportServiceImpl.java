package com.avklm.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avklm.apidata.AirportRestAPIDataImpl;
import com.avklm.error.AirportCustomConstants;
import com.avklm.error.AirportCustomException;
import com.avklm.metrics.MetricsHelper;
import com.avklm.model.AirportResponseList;
import com.avklm.model.AirportResposeCode;
import com.avklm.model.Location;
import com.avklm.model.metrics.RestApiMetric;


@Service
public class AirportServiceImpl implements AirportService{

	private static Logger log = LoggerFactory.getLogger(AirportFareServiceImpl.class);
	
	@Autowired
	AirportRestAPIDataImpl restClient;
	
	@Override
	public List<RestApiMetric>  metricsData(){
		log.info("Inside metrics Data");
		return MetricsHelper.metricDataResponse.getMetricsData().values().stream()
                .collect(Collectors.toList());
		
	}
		
	@Override
	public AirportResponseList getAirports(Long pageNumber,Long size,String term,String sortBy) throws AirportCustomException {
	
	return populateAirports(restClient.getAirports(pageNumber,size,term),sortBy); 
	}
	
	private AirportResponseList populateAirports(AirportResposeCode codes,String sort){
		
		AirportResponseList list = new AirportResponseList();
		list.setPage(codes.getPage());
		switch (sort) {
		case AirportCustomConstants.SORTBY_CODE:
		log.info("INSIDE CODE"+sort);
			list.setLocations(codes.getEmbedded().getLocations().stream().sorted(Comparator.comparing(Location::getCode)).collect(Collectors.toList()));
			break;
		case AirportCustomConstants.SORTBY_NAME:
			list.setLocations(codes.getEmbedded().getLocations().stream().sorted(Comparator.comparing(Location::getName)).collect(Collectors.toList()));
			break;
		case AirportCustomConstants.SORTBY_DESC:
			list.setLocations(codes.getEmbedded().getLocations().stream().sorted(Comparator.comparing(Location::getDescription)).collect(Collectors.toList()));
			break;
		default:
		log.info("DEFAULT SORT	"+sort);
			list.setLocations(codes.getEmbedded().getLocations().stream().sorted(Comparator.comparing(Location::getCode)).collect(Collectors.toList()));
			break;
		}
		
		
		return list;
	}

}
