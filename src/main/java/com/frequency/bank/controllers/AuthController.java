package com.frequency.bank.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frequency.bank.dtos.LoginRequest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor

public class AuthController {
	
	private final AuthenticationManager authenticationManager;
	
	public ResponseEntity<Void> login(@Valid @RequestBody LoginRequest request){
		
		authenticationManager.authenticate(null);
		
		return ResponseEntity.ok().build();
		
	}

}
