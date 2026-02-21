package com.frequency.bank.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.frequency.bank.dtos.CardDto;
import com.frequency.bank.dtos.CreateCardRequest;
import com.frequency.bank.mappers.CardMapper;
import com.frequency.bank.repositories.AccountRepository;
import com.frequency.bank.repositories.CardRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CardService {
	
	private final CardRepository cardRepository;
	private final CardMapper cardMapper;
	private final AccountRepository accountRepository;
	
	public Iterable<CardDto> getAllCards(){
		return cardRepository.findAll()
				.stream().map(cardMapper::toDto).toList();
	}
	
	public CardDto getCard(UUID cardId) {
		var card = cardRepository.findById(cardId).orElseThrow();
		return cardMapper.toDto(card);
	}
	
	public void createCard(UUID accountId, CreateCardRequest request) {
		var account = accountRepository.findById(accountId).orElseThrow();
		var card = cardMapper.toEntity(request);
		card.setAccount(account);
		card.setCustomer(account.getCustomer());
		card.setCardNumber(card.generateCardNumber());
		card.setCvv(card.generateCvv());
		cardRepository.save(card);
	}
	
	public void deleteCard(UUID cardId) {
		var card = cardRepository.findById(cardId).orElseThrow();
		cardRepository.delete(card);
	}

}
