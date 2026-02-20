package com.frequency.bank.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.frequency.bank.dtos.AccountDto;
import com.frequency.bank.dtos.ChangeAccountTypeRequest;
import com.frequency.bank.dtos.CreateAccountRequest;
import com.frequency.bank.entities.AccountType;
import com.frequency.bank.mappers.AccountMapper;
import com.frequency.bank.repositories.AccountRepository;
import com.frequency.bank.repositories.BranchRepository;
import com.frequency.bank.repositories.CustomerRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountService {
	
	private final AccountRepository accountRepository;
	private final AccountMapper accountMapper;
	private final CustomerRepository customerRepository;
	private final BranchRepository branchRepository;
	
	
	public Iterable<AccountDto> getAllAccounts(){
		return accountRepository.findAll()
				.stream()
				.map(accountMapper::toDto).toList();
	}
	
	public AccountDto getAccount(UUID accountId) {
		
		var account  = accountRepository.findById(accountId).orElseThrow();
		return accountMapper.toDto(account);
		
	}
	
	public void createAccount(UUID customerId, CreateAccountRequest request) {
		var branch = branchRepository.findByBranchName(request.getBranchName()).orElseThrow();
		var customer = customerRepository.findById(customerId).orElseThrow();
		var account = accountMapper.toEntity(request);
		account.setBranch(branch);
		account.setAccountNumber(account.generateAccountNumber());
		account.setCustomer(customer);
		accountRepository.save(account);
		customer.getAccounts().add(account);
		customerRepository.save(customer);
	}
	
	public void deleteAccount(UUID accountId) {
		var account = accountRepository.findById(accountId).orElseThrow();
		accountRepository.delete(account);
	}
	
	public void changeAccountType(UUID accountId, ChangeAccountTypeRequest request) {
		
		var account = accountRepository.findById(accountId).orElseThrow();
		var type = request.getAccountType();
		AccountType accountType = AccountType.valueOf(type.toUpperCase());
		account.setAccountType(accountType);
		accountRepository.save(account);	
		
	}

}
