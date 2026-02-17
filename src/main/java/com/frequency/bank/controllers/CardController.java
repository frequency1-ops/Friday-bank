package com.frequency.bank.controllers;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frequency.bank.dtos.CardDto;
import com.frequency.bank.mappers.CardMapper;
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
	
	@GetMapping("/{id}")
	public ResponseEntity<CardDto> getCard(
			@PathVariable(name = "id") UUID cardId
			){
		var card = cardRepository.findById(cardId).orElseThrow();
		return ResponseEntity.ok(cardMapper.toDto(card));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
