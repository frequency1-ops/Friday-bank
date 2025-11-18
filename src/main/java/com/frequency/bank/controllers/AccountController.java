package com.frequency.bank.controllers;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frequency.bank.dtos.AccountDto;
import com.frequency.bank.mappers.AccountMapper;
import com.frequency.bank.repositories.AccountRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("accounts")
public class AccountController {
	
	private final AccountMapper accountMapper;
	private final AccountRepository accountRepository;
	
	@GetMapping
	public Iterable<AccountDto> getAllAccounts(){
		return accountRepository.findAll()
				.stream()
				.map(accountMapper::toDto).toList();
	}
	@GetMapping("/{id}")
	public ResponseEntity<AccountDto> getAccount(
			@PathVariable(name = "id") UUID accountId
			){
		var account = accountRepository.findById(accountId).orElseThrow();
		return ResponseEntity.ok(accountMapper.toDto(account));
	}
}
