package com.rahul.library.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(LibraryException.class)
	public ResponseEntity<ExceptionResponse> handleAllException(LibraryException ex, WebRequest req) {

		ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(), ex.getCode());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);

	}

}
