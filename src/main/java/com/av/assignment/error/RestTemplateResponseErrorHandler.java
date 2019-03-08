package com.av.assignment.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class RestTemplateResponseErrorHandler extends ResponseEntityExceptionHandler  {

	  @ExceptionHandler(AirportNotFoundException.class)
	  public final ResponseEntity<AirportServiceException> handleUserNotFoundException(AirportNotFoundException ex, WebRequest request) {
		  AirportServiceException errorDetails = new AirportServiceException(ex.getMessage(),
	        request.getDescription(false));
	    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	  }
}