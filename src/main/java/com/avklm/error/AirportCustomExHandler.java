package com.avklm.error;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class AirportCustomExHandler {

  private static final Logger LOGGER =
      Logger.getLogger(AirportCustomExHandler.class.getName());



  @ExceptionHandler({AirportCustomException.class})
  @ResponseBody
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public AirportCustomError serviceError(HttpServletResponse httpRes,
      AirportCustomException exception) {

    String errorCode = exception.getcustError().getErrorCode();

    if (errorCode.equalsIgnoreCase(AirportCustomConstants.NO_DATA_FOUND_E1003)) {
      httpRes.setStatus(HttpStatus.NOT_FOUND.value());
    } else if (errorCode.equalsIgnoreCase(AirportCustomConstants.MISSING_INPUT_ERROR_E1001)) {
      httpRes.setStatus(HttpStatus.BAD_REQUEST.value());
    } else if (errorCode
        .equalsIgnoreCase(AirportCustomConstants.ERROR_INVALID_PARAM_COMBINATION_E1005)) {
      httpRes.setStatus(HttpStatus.FORBIDDEN.value());
    } else if (errorCode.equalsIgnoreCase(AirportCustomConstants.UNAUTHORIZED_E1006)) {
      httpRes.setStatus(HttpStatus.UNAUTHORIZED.value());
    }

    else {
      httpRes.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    LOGGER.log(Level.SEVERE,
        "DataServiceException Caught in Handler - " + exception.getcustError().getErrorCode() + "-"
            + exception.getcustError().getErrorMessage() + "-"
            + exception.getcustError().getUserMessage());

    return exception.getcustError();
  }

}
