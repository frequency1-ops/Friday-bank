package com.frequency.bank.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.frequency.bank.dtos.ErrorDto;
import com.frequency.bank.exceptions.CustomerNotFoundException;
import com.frequency.bank.exceptions.WrongPasswordException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationErrors(
			MethodArgumentNotValidException exception
			){
		var errors = new HashMap<String, String>();
		exception.getBindingResult().getFieldErrors().forEach(error -> {
			errors.put(error.getField(), error.getDefaultMessage());
		});
		return ResponseEntity.badRequest().body(errors);
	}
	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<ErrorDto> handleCustomerNotFoundException(){
		
		return ResponseEntity.badRequest().body(new ErrorDto("No customer with that ID"));
	}
	@ExceptionHandler(WrongPasswordException.class)
	public ResponseEntity<ErrorDto> handleWrongPassword() {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorDto("Wrong password"));
	}
}
