package com.frequency.bank.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.frequency.bank.dtos.EmployeeDto;
import com.frequency.bank.entities.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
	
	@Mapping(target = "branchName", expression = "java(employee.getBranch().getBranchName())")
	EmployeeDto toDto(Employee employee);
}
