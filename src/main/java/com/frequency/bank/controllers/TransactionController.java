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

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/transactions")
@AllArgsConstructor
public class TransactionController {
	
	private final TransactionRepository transactionRepository;
	private final TransactionMapper transactionMapper;
	private final AccountRepository accountRepository;
	
	@GetMapping
	public ResponseEntity<Iterable<TransactionDto>> getAllTransactions(){
		
		return ResponseEntity.ok(transactionRepository.findAll()
				.stream().
				map(transactionMapper::toDto).toList());
	}
	@GetMapping("/{id}")
	public ResponseEntity<TransactionDto> getTransaction(
			@PathVariable(name = "id") UUID transactionId
			){
		var transaction = transactionRepository.findById(transactionId).orElseThrow();
		return ResponseEntity.ok(transactionMapper.toDto(transaction));
	}
	@PostMapping("/{id}")
	public ResponseEntity<TransactionDto> createTransaction(
			@PathVariable(name = "id") UUID accountId,
			@RequestBody TransactionRequest request
			){
		var account = accountRepository.findById(accountId).orElseThrow();
		var transaction = transactionMapper.toEntity(request);
		switch(transaction.getType()){
			case DEPOSIT:
				account.setBalance(account.getBalance().add(transaction.getAmount()));
				break;
			case WITHDRAWAL:
				account.setBalance(account.getBalance().subtract(transaction.getAmount()));
				break;
			case TRANSFER_OUT:
				var recipientAccount = accountRepository.findByAccountNumber(request.getRecipientAccountNumber()).orElseThrow();
				var recipientTransaction = transactionMapper.toEntity(request);
				recipientTransaction.setAccount(recipientAccount);
				recipientTransaction.setType(TransactionType.TRANSFER_IN);
				recipientAccount.setBalance(recipientAccount.getBalance().add(recipientTransaction.getAmount()));
				break;
			default:
				break;
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(transactionMapper.toDto(transaction));
	}
}
