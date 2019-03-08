package com.avklm.error;

import java.io.Serializable;

public class AirportCustomException extends RuntimeException implements Serializable {

  private static final long serialVersionUID = -1L;
  private AirportCustomError custError;

  public AirportCustomException() {
    super();
  }

  public AirportCustomException(String message, Exception exception) {
    super(exception.getMessage());
    this.custError =
        new AirportCustomError(AirportCustomConstants.GENERIC_ERROR, exception.getMessage());
  }

  public AirportCustomException(String errorCode, String message) {
    super(message);
    this.custError = new AirportCustomError(errorCode, message);
  }

  public AirportCustomException(String errorCode) {
    super();
    this.custError = new AirportCustomError(errorCode);
  }

  public AirportCustomException(String type, String errorCode, String status, String message,
      String userMsg, String[] errors) {
    super(message);
    this.custError = new AirportCustomError(type, errorCode, status, message, userMsg, errors);
  }

  public AirportCustomException(String type, String errorCode, String status, String message,
      String userMsg) {
    super(message);
    this.custError = new AirportCustomError(type, errorCode, status, message, userMsg);
  }

  public AirportCustomError getcustError() {
    return custError;
  }

  public void setcustError(AirportCustomError custError) {
    this.custError = custError;
  }
}
