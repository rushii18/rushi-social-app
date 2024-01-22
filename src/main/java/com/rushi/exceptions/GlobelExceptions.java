package com.rushi.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobelExceptions {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> otherExceptionhandler(Exception exp, WebRequest req) {
		ErrorDetails errorDetails = new ErrorDetails(exp.getMessage(), req.getDescription(false), LocalDateTime.now());

		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);

	}

}
