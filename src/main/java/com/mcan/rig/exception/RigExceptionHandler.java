package com.mcan.rig.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;


@ControllerAdvice
public class RigExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<Object> handleRuntimeException (RuntimeException e, WebRequest request) {
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;
        ErrorResponse response = ErrorResponse.builder()
                                              .message(e.getLocalizedMessage())
                                              .method(servletWebRequest.getHttpMethod().name())
                                              .path(servletWebRequest.getRequest().getRequestURI())
                                              .timestamp(new Date()).build();
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }
}