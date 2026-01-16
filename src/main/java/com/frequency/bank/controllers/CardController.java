package com.frequency.bank.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frequency.bank.dtos.CardDto;
import com.frequency.bank.mappers.CardMapper;
import com.frequency.bank.repositories.AccountRepository;
import com.frequency.bank.repositories.CardRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/cards")
@AllArgsConstructor
public class CardController {
	
	private final CardRepository cardRepository;
	private final CardMapper cardMapper;
	
	@GetMapping
	public ResponseEntity<Iterable<CardDto>> getAllCards(){
		return ResponseEntity.ok(cardRepository.findAll()
				.stream().map(cardMapper::toDto).toList());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
