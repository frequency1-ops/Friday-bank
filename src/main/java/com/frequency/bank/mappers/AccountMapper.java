package com.frequency.bank.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.frequency.bank.dtos.AccountDto;
import com.frequency.bank.dtos.CreateAccountRequest;
import com.frequency.bank.entities.Account;

@Mapper(componentModel = "spring")
public interface AccountMapper {
	@Mapping(target = "customerId", expression = "java(account.getCustomer().getCustomerId())")
	@Mapping(target = "branchId", expression = "java(account.getBranch().getBranchId())")
	AccountDto toDto(Account account);
	Account toEntity(CreateAccountRequest request);
}
