package com.frequency.bank.mappers;

import org.mapstruct.Mapper;

import com.frequency.bank.dtos.CustomerDto;
import com.frequency.bank.dtos.RegisterCustomerRequest;
import com.frequency.bank.entities.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
	
	CustomerDto toDto(Customer customer);
	Customer toEntity(RegisterCustomerRequest registerCustomerRequest);
	
}
