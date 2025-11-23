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
import com.frequency.bank.service.CustomerService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	private final CustomerService customerService;
	
	@GetMapping
	public ResponseEntity<Iterable<CustomerDto>> getAllCustomers(){
		return ResponseEntity.ok(customerService.getAllCustomers());  
	}
	
	@GetMapping("/{id}")
	private ResponseEntity<CustomerDto> getCustomerById(@PathVariable(name = "id") UUID customerId){
		return ResponseEntity.ok(customerService.getCustomerById(customerId));
	}
	@PostMapping()
	public ResponseEntity<CustomerDto> registerCustomer(
			@RequestBody @Valid RegisterCustomerRequest request,
			UriComponentsBuilder uriBuilder
			){
		
		var customerDto = customerService.registerCustomer(request);
		var uri = uriBuilder.path("/customers/{customerId}").buildAndExpand(customerDto.getCustomerId()).toUri();
		return ResponseEntity.created(uri).body(customerDto);
		
	}
	@PutMapping("/{id}")
	public ResponseEntity<CustomerDto> updateCustomer(
			@PathVariable(name = "id") UUID customerId,
			@RequestBody UpdateCustomerRequest request
			){
		
		var customerDto = customerService.updateCustomer(request, customerId);
		return ResponseEntity.ok(customerDto);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCustomer(
			@PathVariable(name = "id") UUID customerId
			){
		
		customerService.deleteCustomer(customerId);
		
		return ResponseEntity.noContent().build();
	}
	@PostMapping("/{id}/change-password")
	public ResponseEntity<Void> changePassword(
			@PathVariable(name = "id") UUID customerId,
			@RequestBody ChangePasswordRequest request
			){
		customerService.changePassword(customerId, request);
		return ResponseEntity.noContent().build();
	}
}
