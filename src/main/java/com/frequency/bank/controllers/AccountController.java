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
import com.frequency.bank.service.AccountService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/accounts")
public class AccountController {

	private final AccountService accountService;
	
	@GetMapping
	public ResponseEntity<Iterable<AccountDto>> getAllAccounts(){
		return ResponseEntity.ok(accountService.getAllAccounts());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AccountDto> getAccount(
			@PathVariable(name = "id") UUID accountId
			){
		return ResponseEntity.ok(accountService.getAccount(accountId));
	}
	@PostMapping("/{id}/create-account")
	public ResponseEntity<Void> createAccount(
			@PathVariable(name = "id") UUID customerId,
			@RequestBody CreateAccountRequest request
			){
			
			accountService.createAccount(customerId, request);
			
		return new ResponseEntity<>(HttpStatus.CREATED);
		
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAccount(
			@PathVariable(name = "id") UUID  accountId
			){
		
		return ResponseEntity.noContent().build();
	}
	@PostMapping("/{id}/change-accountType")
	public ResponseEntity<Void> changeAccountType(
			@PathVariable(name = "id") UUID accountId,
			@RequestBody ChangeAccountTypeRequest request
			){
		accountService.changeAccountType(accountId, request);
		return ResponseEntity.noContent().build();
		
	}
}
