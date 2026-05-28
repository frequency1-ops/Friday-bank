package com.frequency.bank.controllers;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frequency.bank.dtos.CustomerDto;
import com.frequency.bank.dtos.JwtResponse;
import com.frequency.bank.dtos.LoginRequest;
import com.frequency.bank.mappers.CustomerMapper;
import com.frequency.bank.repositories.CustomerRepository;
import com.frequency.bank.service.JwtService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor

public class AuthController {
	
	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;
	private final CustomerRepository customerRepository;
	private final CustomerMapper customerMapper;
	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginRequest request, HttpServletResponse response){
		
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		
		var customer = customerRepository.findByEmail(request.getEmail()).orElseThrow();
		var accessToken = jwtService.generateAccessToken(customer);
		var refreshToken = jwtService.generateRefreshToken(customer);
		var cookie = new Cookie("refreshToken", refreshToken);
		cookie.setHttpOnly(true);
		cookie.setMaxAge(604800);
		cookie.setSecure(true);
		cookie.setPath("/auth/refresh");
		response.addCookie(cookie);
		return ResponseEntity.ok(new JwtResponse(accessToken));
		//	
	}
	@GetMapping("/me")
	public ResponseEntity<CustomerDto> me(){
		var authentication = SecurityContextHolder.getContext().getAuthentication();
		var customerId = UUID.fromString((String)authentication.getPrincipal());
		var customer = customerRepository.findById(customerId).orElse(null);
		if(customer == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(customerMapper.toDto(customer));
	}
	@PostMapping("/validate")
	public boolean validate(@RequestHeader("Authorization") String token) {
		return jwtService.validateToken(token);
	}
	

}
