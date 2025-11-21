package com.frequency.bank.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.frequency.bank.dtos.CustomerDto;
import com.frequency.bank.dtos.RegisterCustomerRequest;
import com.frequency.bank.dtos.UpdateCustomerRequest;
import com.frequency.bank.entities.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
	
	CustomerDto toDto(Customer customer);
	
	Customer toEntity(RegisterCustomerRequest registerCustomerRequest);
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void updateCustomer(UpdateCustomerRequest request, @MappingTarget Customer customer);
	
}
