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

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/cards")
@AllArgsConstructor
public class CardController {
	
	private final CardRepository cardRepository;
	private final CardMapper cardMapper;
	private final AccountRepository accountRepository;
	
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
	@PostMapping("/{id}/create-card")
	public ResponseEntity<Void> createCard(
			@PathVariable(name= "id") UUID accountId,
			@RequestBody CreateCardRequest request
			){
		var account = accountRepository.findById(accountId).orElseThrow();
		var card = cardMapper.toEntity(request);
		card.setAccount(account);
		card.setCustomer(account.getCustomer());
		card.setCardNumber(card.generateCardNumber());
		card.setCvv(card.generateCvv());
		cardRepository.save(card);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCard(
			@PathVariable(name = "id") UUID cardId
			){
		var card = cardRepository.findById(cardId).orElseThrow();
		cardRepository.delete(card);
		return ResponseEntity.noContent().build();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
