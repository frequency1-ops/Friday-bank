package com.frequency.bank.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.frequency.bank.dtos.ChangePasswordRequest;
import com.frequency.bank.dtos.CustomerDto;
import com.frequency.bank.dtos.RegisterCustomerRequest;
import com.frequency.bank.dtos.UpdateCustomerRequest;
import com.frequency.bank.entities.Account;
import com.frequency.bank.entities.AccountType;
import com.frequency.bank.entities.Customer;
import com.frequency.bank.exceptions.CustomerNotFoundException;
import com.frequency.bank.exceptions.WrongPasswordException;
import com.frequency.bank.mappers.CustomerMapper;
import com.frequency.bank.repositories.AccountRepository;
import com.frequency.bank.repositories.BranchRepository;
import com.frequency.bank.repositories.CustomerRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerService {
	
	private final CustomerMapper customerMapper;
	private final CustomerRepository customerRepository;
	private final BranchRepository branchRepository;
	private final AccountRepository accountRepository;
	
	public Iterable<CustomerDto> getAllCustomers(){
		return customerRepository.findAll().stream()
				.map(customerMapper::toDto).toList();
	}
	
	public CustomerDto getCustomerById(UUID customerId) {
		var customer = customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
		
		return customerMapper.toDto(customer);
		}
	public CustomerDto registerCustomer(RegisterCustomerRequest request) {
		var customer = customerMapper.toEntity(request);
		customerRepository.save(customer);
		
		var branch = branchRepository.findByBranchName(request.getBranchName()).orElseThrow();
		
		var account = new Account();
		account.setCustomer(customer);
		account.setBranch(branch);
		account.setAccountNumber(account.generateAccountNumber());
		account.setAccountType(AccountType.SAVINGS);
		accountRepository.save(account);
		return customerMapper.toDto(customer);
	}
	
	public CustomerDto updateCustomer(UpdateCustomerRequest request, UUID customerId) {
		var customer = customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
		customerMapper.updateCustomer(request, customer);
		customerRepository.save(customer);
		return customerMapper.toDto(customer);
	}
	
	public void deleteCustomer(UUID customerId) {
		customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
		customerRepository.deleteById(customerId);
	}
	
	public void changePassword(UUID customerId, ChangePasswordRequest request) {
		var customer = customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
		if(!customer.getPassword().equals(request.getOldPassword())) {
			throw new WrongPasswordException();
		}
		customer.setPassword(request.getNewPassword());
		customerRepository.save(customer);
	}
	
	
	
	
	
	
	
	


}
