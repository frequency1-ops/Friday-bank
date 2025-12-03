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

import com.frequency.bank.dtos.AccountDto;
import com.frequency.bank.dtos.ChangeAccountTypeRequest;
import com.frequency.bank.dtos.CreateAccountRequest;
import com.frequency.bank.entities.AccountType;
import com.frequency.bank.mappers.AccountMapper;
import com.frequency.bank.repositories.AccountRepository;
import com.frequency.bank.repositories.CustomerRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("accounts")
public class AccountController {
	
	private final AccountMapper accountMapper;
	private final AccountRepository accountRepository;
	private final CustomerRepository customerRepository;
	
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
	@PostMapping("/{id}/create-account")
	public ResponseEntity<Void> createAccount(
			@PathVariable(name = "id") UUID customerId,
			@RequestBody CreateAccountRequest request
			){
		var customer = customerRepository.findById(customerId).orElseThrow();
		var account = accountMapper.toEntity(request);
		account.setAccountNumber(account.getAccountNumber());
		account.setCustomer(customer);
		accountRepository.save(account);
		customer.getAccounts().add(account);
		customerRepository.save(customer);
		//Hello comment
		
		return new ResponseEntity<>(HttpStatus.CREATED);
		//nothing
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAccount(
			@PathVariable(name = "id") UUID  accountId
			){
		var account = accountRepository.findById(accountId).orElseThrow();
		accountRepository.delete(account);
		return ResponseEntity.noContent().build();
	}
	@PostMapping("/{id}/change-accountType")
	public ResponseEntity<Void> changeAccountType(
			@PathVariable(name = "id") UUID accountId,
			@RequestBody ChangeAccountTypeRequest request
			){
		var account = accountRepository.findById(accountId).orElseThrow();
		var type = request.getAccountType();
		AccountType accountType = AccountType.valueOf(type.toUpperCase());
		account.setAccountType(accountType);
		accountRepository.save(account);	
		return ResponseEntity.noContent().build();
		
	}
}
