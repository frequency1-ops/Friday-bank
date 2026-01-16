package com.frequency.bank.mappers;

import org.mapstruct.Mapper;

import com.frequency.bank.dtos.TransactionDto;
import com.frequency.bank.dtos.TransactionRequest;
import com.frequency.bank.entities.Transaction;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
	
	TransactionDto toDto(Transaction transaction);
	TransactionRequest toEntity(TransactionRequest request);

}
