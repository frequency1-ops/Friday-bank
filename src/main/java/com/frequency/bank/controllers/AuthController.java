package com.frequency.bank.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frequency.bank.dtos.JwtResponse;
import com.frequency.bank.dtos.LoginRequest;
import com.frequency.bank.service.JwtService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor

public class AuthController {
	
	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;
	
	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginRequest request){
		
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		
		var token = jwtService.generateToken(request.getEmail());
		
		
		return ResponseEntity.ok(new JwtResponse(token));
		//
		
	}

}
