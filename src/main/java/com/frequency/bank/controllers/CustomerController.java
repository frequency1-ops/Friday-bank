package com.frequency.bank.controllers;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.frequency.bank.dtos.ChangePasswordRequest;
import com.frequency.bank.dtos.CustomerDto;
import com.frequency.bank.dtos.RegisterCustomerRequest;
import com.frequency.bank.dtos.UpdateCustomerRequest;
import com.frequency.bank.exceptions.CustomerNotFoundException;
import com.frequency.bank.mappers.CustomerMapper;
import com.frequency.bank.repositories.CustomerRepository;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/customers")
public class CustomerController {
	private final CustomerRepository customerRepository;
	private final CustomerMapper customerMapper;
	
	@GetMapping
	public Iterable<CustomerDto> getAllCustomers(){
		return customerRepository.findAll().stream()
				.map(customerMapper::toDto).toList();
	}
	
	@GetMapping("/{id}")
	private ResponseEntity<CustomerDto> getCustomerById(@PathVariable(name = "id") UUID customerId){
		var customer = customerRepository.findById(customerId).orElse(null);
		if (customer == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(customerMapper.toDto(customer));
	}
	@PostMapping()
	public ResponseEntity<CustomerDto> registerCustomer(
			@RequestBody @Valid RegisterCustomerRequest request,
			UriComponentsBuilder uriBuilder
			){
		
		var customer = customerMapper.toEntity(request);
		customerRepository.save(customer);
		var customerDto = customerMapper.toDto(customer);
		var uri = uriBuilder.path("/customers/{customerId}").buildAndExpand(customerDto.getCustomerId()).toUri();
		return ResponseEntity.created(uri).body(customerDto);
		
	}
	@PutMapping("/{id}")
	public ResponseEntity<CustomerDto> updateCustomer(
			@PathVariable(name = "id") UUID customerId,
			@RequestBody UpdateCustomerRequest request
			){
		
		var customer = customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
		customerMapper.updateCustomer(request, customer);
		customerRepository.save(customer);
		return ResponseEntity.ok(customerMapper.toDto(customer));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCustomer(
			@PathVariable(name = "id") UUID customerId
			){
		
		customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
		customerRepository.deleteById(customerId);
		
		return ResponseEntity.noContent().build();
	}
	@PostMapping("/{id}/change-password")
	public ResponseEntity<Void> changePassword(
			@PathVariable(name = "id") UUID customerId,
			@RequestBody ChangePasswordRequest request
			){
		var customer = customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
		if(!customer.getPassword().equals(request.getOldPassword())) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		customer.setPassword(request.getNewPassword());
		customerRepository.save(customer);
		return ResponseEntity.noContent().build();
	}
}
