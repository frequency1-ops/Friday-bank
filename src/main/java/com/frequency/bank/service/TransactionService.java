package com.frequency.bank.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.frequency.bank.dtos.TransactionDto;
import com.frequency.bank.dtos.TransactionRequest;
import com.frequency.bank.entities.TransactionType;
import com.frequency.bank.mappers.TransactionMapper;
import com.frequency.bank.repositories.AccountRepository;
import com.frequency.bank.repositories.TransactionRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TransactionService {
	
	private final TransactionRepository transactionRepository;
	private final TransactionMapper transactionMapper;
	private final AccountRepository accountRepository;
	
	public Iterable<TransactionDto> getAllTransactions(){
		return transactionRepository.findAll()
		.stream().
		map(transactionMapper::toDto).toList();
	}
	
	public TransactionDto getTransaction(UUID transactionId) {
		var transaction = transactionRepository.findById(transactionId).orElseThrow();
		return transactionMapper.toDto(transaction);
	}
	
	public Iterable<TransactionDto> getAccountTransactionHistory(UUID accountId){
		var account = accountRepository.findById(accountId).orElseThrow();
		var history = account.getTransactions();
		
		return history.stream().map(transactionMapper::toDto).toList();
	}
	
	public TransactionDto createTrnsaction(UUID accountId, TransactionRequest request) {
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
		return transactionMapper.toDto(transaction);
	}

}
