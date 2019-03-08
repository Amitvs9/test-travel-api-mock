package com.avklm.service;

import java.util.List;

import com.avklm.error.AirportCustomException;
import com.avklm.model.AirportResponseList;
import com.avklm.model.metrics.RestApiMetric;

public interface AirportService {
	public List<RestApiMetric>  metricsData()throws AirportCustomException;
	public AirportResponseList getAirports(Long pageNumber,Long size,String term,String sortBy) throws AirportCustomException;

}
