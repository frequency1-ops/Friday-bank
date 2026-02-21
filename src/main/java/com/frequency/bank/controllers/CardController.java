package com.frequency.bank.controllers;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frequency.bank.dtos.CardDto;
import com.frequency.bank.dtos.CreateCardRequest;
import com.frequency.bank.mappers.CardMapper;
import com.frequency.bank.repositories.AccountRepository;
import com.frequency.bank.repositories.CardRepository;
import com.frequency.bank.service.CardService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/cards")
@AllArgsConstructor
public class CardController {
	
	private final CardService cardService;
	
	@GetMapping
	public ResponseEntity<Iterable<CardDto>> getAllCards(){
		return ResponseEntity.ok(cardService.getAllCards());
	}
	
	@GetMapping("/{card-id}")
	public ResponseEntity<CardDto> getCard(
			@PathVariable(name = "card-id") UUID cardId
			){
			var cardDto = cardService.getCard(cardId);
		return ResponseEntity.ok(cardDto);
	}
	@PostMapping("/{account-id}/create-card")
	public ResponseEntity<Void> createCard(
			@PathVariable(name= "account-id") UUID accountId,
			@RequestBody CreateCardRequest request
			){
		cardService.createCard(accountId, request);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	@DeleteMapping("/{card-id}")
	public ResponseEntity<Void> deleteCard(
			@PathVariable(name = "card-id") UUID cardId
			){
		cardService.deleteCard(cardId);
		return ResponseEntity.noContent().build();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
