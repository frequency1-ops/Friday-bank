package com.frequency.bank.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.frequency.bank.dtos.LoanApplicationRequest;
import com.frequency.bank.dtos.LoanDto;
import com.frequency.bank.entities.Loan;

@Mapper(componentModel = "spring")
public interface LoanMapper {
	
	LoanDto toDto(Loan loan);
	 @Mapping(target = "startDate", ignore = true)
	 @Mapping(target = "endDate", ignore = true)
	Loan toEntity(LoanApplicationRequest request);

}
