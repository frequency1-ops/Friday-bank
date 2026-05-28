package com.frequency.bank.service;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.frequency.bank.entities.Customer;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	@Value("${spring.jwt.secret}")
	private String secret;
	
	public String generateAccessToken(Customer customer) {
		
		final long tokenExpiration = 300;
		
		return generateToken(customer, tokenExpiration);
		
	}
public String generateRefreshToken(Customer customer) {
		
		final long tokenExpiration = 604800;
		
		return generateToken(customer, tokenExpiration);
		
		
	}

	private String generateToken(Customer customer, final long tokenExpiration) {
		return Jwts.builder()
			.subject(customer.getCustomerId().toString())
			.claim("email", customer.getEmail())
			.claim("name", customer.getFirstName())
			.issuedAt(new Date())
			.expiration(new Date(System.currentTimeMillis() + 1000 * tokenExpiration))
			.signWith(Keys.hmacShaKeyFor(secret.getBytes()))
			.compact();
	}
	
	public boolean validateToken(String token) {
		try {
			var claims = getClaims(token);
			return claims.getExpiration().after(new Date());
			
		} catch (JwtException exception) {
			return false;
		}
		
	}

	private Claims getClaims(String token) {
		return Jwts.parser()
				.verifyWith(Keys.hmacShaKeyFor(secret.getBytes())).build().parseSignedClaims(token)
					.getPayload();
		
	}
	public UUID getIdFromToken(String token) {
		return UUID.fromString(getClaims(token).getSubject()); 
	}

}
