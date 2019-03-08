package com.av.assignment.error;

public class AirportServiceException {
	
	 private String message;
	  private String details;

	  public AirportServiceException(String message, String details) {
	    super();
	    this.message = message;
	    this.details = details;
	  }
	  
	  

}
