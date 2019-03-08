package com.avklm.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.avklm.config.SecurityConfig;
import com.avklm.error.AirportCustomConstants;
import com.avklm.error.AirportCustomException;
import com.avklm.model.AirportResposeCode;
import com.avklm.model.Fare;
import com.avklm.model.FareDetails;
import com.avklm.model.Location;


@Component
public class AirportDataImpl {

	private static Logger log = LoggerFactory.getLogger(AirportDataImpl.class);
	@Autowired
	private SecurityConfig airportConfig;

	@Autowired
	@Qualifier("oAuth2RestTemplate")
	private RestTemplate oAuth2RestTemplate;

	public FareDetails getFareData(String origin, String destination, String currency) throws AirportCustomException {
		log.info("starting getFareData");
		FareDetails details = new FareDetails();
		Map<String, String> uriVariables = null;

		try {
			uriVariables = new HashMap<>();
			uriVariables.put(AirportCustomConstants.ORIGIN_PATH_PARAM, origin);
			uriVariables.put(AirportCustomConstants.DEST_PATH_PARAM, destination);

			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(airportConfig.getFareUrl())
					.queryParam(AirportCustomConstants.CURRENCY_QUERY_PARAM, currency);

			ResponseEntity<Fare> fare = oAuth2RestTemplate.exchange(
					builder.buildAndExpand(uriVariables).toUri().toString(), HttpMethod.GET, null, Fare.class,
					uriVariables);

			if (fare.getStatusCode().value() == HttpStatus.OK.value()) {
				details.setFare(fare.getBody());

			} else {
				if (fare.getStatusCode().value() == HttpStatus.NOT_FOUND.value()) {
					log.debug("getFareData  Data Not Found::");
					throw new AirportCustomException(AirportCustomConstants.NO_DATA_FOUND_E1003,
							fare.getBody().toString());
				} else if (fare.getStatusCode().value() == HttpStatus.BAD_REQUEST.value()) {
					log.debug("getFareData  BAD Request::");
					throw new AirportCustomException(AirportCustomConstants.MISSING_INPUT_ERROR_E1001,
							fare.getBody().toString());
				}
			}

		} catch (HttpClientErrorException he) {
			log.error("HttpClientErrorException in getFareData ::" + he.getLocalizedMessage());
			throw new RuntimeException(AirportCustomConstants.ERROR_GETTING_CONNECTION);
		} catch(IllegalArgumentException ie){
			log.error("Exception occured in getFareData IllegalArgumentException::" + ie.getLocalizedMessage());
			 throw new AirportCustomException(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
					 ie.getLocalizedMessage());
		}catch (Exception e) {
			log.error("Exception occured in getFareData ::" + e.getLocalizedMessage());
			throw new AirportCustomException(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
					 e.getLocalizedMessage());
		} finally {
			uriVariables = null;
		}
		log.info("End getFareData");
		return details;
	}

	public Location getOrgDestDetails(String code) throws AirportCustomException {
		log.info("Start getOrgDestDetails");
		try {
			return Optional.of(getCodes(code).getEmbedded().getLocations()
			.stream()
			.map(p -> new Location(p.getCode(), p.getName(), p.getDescription()))
			.collect(Collectors.toList()).get(0)).orElseThrow(AirportCustomException::new);
								
		} catch (Exception e) {
			log.error("Exception occured in getOrgDestDetails ::" + e.getLocalizedMessage());
			throw new AirportCustomException(AirportCustomConstants.ERROR_GETTING_ORIGIN_DESTINATION,e.getLocalizedMessage());
		}
	}

	public AirportResposeCode getCodes(String code) throws AirportCustomException {

		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(airportConfig.getSearchUrl())
				.queryParam(AirportCustomConstants.SEARCH_QUERY_PARAM, code);
		ResponseEntity<AirportResposeCode> locations = null;
		try {
			locations = oAuth2RestTemplate.exchange(builder.buildAndExpand().toUri(), HttpMethod.GET, null,
					AirportResposeCode.class);
			
				if (locations.getStatusCode().value() == HttpStatus.NOT_FOUND.value()) {
					log.debug("getCodes  Data Not Found::");
					throw new AirportCustomException(AirportCustomConstants.NO_DATA_FOUND_E1003,
							locations.getBody().toString());
				} else if (locations.getStatusCode().value() == HttpStatus.BAD_REQUEST.value()) {
					log.debug("getCodes  BAD Request::");
					throw new AirportCustomException(AirportCustomConstants.MISSING_INPUT_ERROR_E1001,
							locations.getBody().toString());
				}
			
		} catch (HttpClientErrorException he) {
			log.error("HttpClientErrorException in getFareData ::" + he.getLocalizedMessage());
			throw new RuntimeException(AirportCustomConstants.ERROR_GETTING_CONNECTION);
		} catch (Exception e) {
			log.error("Exception occured in getFareData ::" + e.getLocalizedMessage());
			 throw new AirportCustomException(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
					 e.getLocalizedMessage());
		} finally {
			builder = null;
		}
		return Optional.of(Optional.of(locations).orElseThrow(AirportCustomException::new).getBody())
		  						                 .orElseThrow(AirportCustomException::new);
		
	}
	
	public AirportResposeCode getAirports(Long pageNumber,Long size,String term) throws AirportCustomException {

		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(airportConfig.getAirportsUrl())
		.queryParam("page", pageNumber.toString())
		.queryParam("size", size.toString());
		if(null!=term && !term.equals("")){
			builder.queryParam("term", term);
		}
		
		ResponseEntity<AirportResposeCode> locations = null;
		try {
			locations = oAuth2RestTemplate.exchange(builder.buildAndExpand().toUri(), HttpMethod.GET, null,
					AirportResposeCode.class);

			if (locations.getStatusCode().value() == HttpStatus.NOT_FOUND.value()) {
					log.debug("getCodes  Data Not Found::");
					throw new AirportCustomException(AirportCustomConstants.NO_DATA_FOUND_E1003,
							locations.getBody().toString());
				} else if (locations.getStatusCode().value() == HttpStatus.BAD_REQUEST.value()) {
					log.debug("getCodes  BAD Request::");
					throw new AirportCustomException(AirportCustomConstants.MISSING_INPUT_ERROR_E1001,
							locations.getBody().toString());
				}
			
		} catch (HttpClientErrorException he) {
			log.error("HttpClientErrorException in getAirports ::" + he.getLocalizedMessage());
			throw new RuntimeException(AirportCustomConstants.ERROR_GETTING_CONNECTION);
		} catch (Exception e) {
			 throw new AirportCustomException(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
					 e.getLocalizedMessage());
		} finally {
			builder = null;
		}
		return Optional.of(Optional.of(locations).orElseThrow(AirportCustomException::new).getBody())
		  						                 .orElseThrow(AirportCustomException::new);
	}

}
