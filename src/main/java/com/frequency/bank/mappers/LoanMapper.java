package com.frequency.bank.mappers;

import org.mapstruct.Mapper;

import com.frequency.bank.dtos.LoanApplicationRequest;
import com.frequency.bank.dtos.LoanDto;
import com.frequency.bank.entities.Loan;

@Mapper(componentModel = "spring")
public interface LoanMapper {
	
	LoanDto toDto(Loan loan);
	Loan toEntity(LoanApplicationRequest request);

}
