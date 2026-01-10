package com.frequency.bank.mappers;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.frequency.bank.dtos.BranchDto;
import com.frequency.bank.entities.Branch;

@Mapper(componentModel = "spring")
public interface BranchMapper {
	
	@Mapping(target = "bankManagerId", expression = "java(branch.getBankManager().getEmployeeId())")
	@Mapping(target = "branchManagerName", expression = "java(branch.getBankManager().getFirstName())")
	BranchDto toDto(Branch branch);
	

}
