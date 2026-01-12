package com.frequency.bank.mappers;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.frequency.bank.dtos.BranchDto;
import com.frequency.bank.dtos.CreateBranchRequest;
import com.frequency.bank.entities.Branch;

@Mapper(componentModel = "spring")
public interface BranchMapper {
	
	BranchDto toDto(Branch branch);
	Branch toEntity(CreateBranchRequest request);
	

}
