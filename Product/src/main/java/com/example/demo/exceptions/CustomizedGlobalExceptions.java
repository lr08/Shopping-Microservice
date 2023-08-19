package com.example.demo.exceptions;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomizedGlobalExceptions extends ResponseEntityExceptionHandler{

	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<Object> handleResourceNotFoundException(Exception ex, WebRequest request) throws Exception {
		ErrorDetails error = new ErrorDetails(LocalDate.now(), ex.getMessage(),request.getDescription(false));
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
}
