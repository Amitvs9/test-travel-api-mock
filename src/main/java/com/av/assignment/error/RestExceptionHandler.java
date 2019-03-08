package com.av.assignment.error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
 
    @ExceptionHandler({AirportNotFoundException.class })
    public ResponseEntity<Object> handleNotFoundException(
      Exception ex, WebRequest request) {
        return new ResponseEntity<Object>(
          "Resource Not Found", new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler({RuntimeException.class })
    public ResponseEntity<Object> returnBadRequestException(
      Exception ex, WebRequest request) {
        return new ResponseEntity<Object>(
          "Bad Request", new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler({AccessDeniedException.class })
    public ResponseEntity<Object> handleAccessDeniedException(
      Exception ex, WebRequest request) {
        return new ResponseEntity<Object>(
          "Access denied!", new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }
     
}