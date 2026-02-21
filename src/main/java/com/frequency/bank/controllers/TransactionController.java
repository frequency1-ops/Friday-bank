package com.frequency.bank.controllers;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frequency.bank.dtos.TransactionDto;
import com.frequency.bank.dtos.TransactionRequest;
import com.frequency.bank.entities.TransactionType;
import com.frequency.bank.mappers.TransactionMapper;
import com.frequency.bank.repositories.AccountRepository;
import com.frequency.bank.repositories.TransactionRepository;
import com.frequency.bank.service.TransactionService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/transactions")
@AllArgsConstructor
public class TransactionController {
	
	private TransactionService transactionService;
	
	@GetMapping
	public ResponseEntity<Iterable<TransactionDto>> getAllTransactions(){
		
		return ResponseEntity.ok(transactionService.getAllTransactions());
	}
	@GetMapping("/{transaction-id}")
	public ResponseEntity<TransactionDto> getTransaction(
			@PathVariable(name = "transaction-id") UUID transactionId
			){
		var transactionDto = transactionService.getTransaction(transactionId);
		return ResponseEntity.ok(transactionDto);
	}
	@GetMapping("/{account-id}/account-history")
	public ResponseEntity<Iterable<TransactionDto>> getAccountTransactionHistory(
				@PathVariable(name = "account-id") UUID accountId
			){
		
		var history = transactionService.getAccountTransactionHistory(accountId);
		return ResponseEntity.ok(history);
	}
	
	@PostMapping("/{account-id}")
	public ResponseEntity<TransactionDto> createTransaction(
			@PathVariable(name = "account-id") UUID accountId,
			@RequestBody TransactionRequest request
			){
		var transactionDto = transactionService.createTrnsaction(accountId, request);
		return ResponseEntity.status(HttpStatus.CREATED).body(transactionDto);
	}
}
