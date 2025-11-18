package com.frequency.bank.mappers;

import org.mapstruct.Mapper;

import com.frequency.bank.dtos.AccountDto;
import com.frequency.bank.entities.Account;

@Mapper(componentModel = "spring")
public interface AccountMapper {
	AccountDto toDto(Account account);
}
