package com.frequency.bank.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.frequency.bank.dtos.AddEmployeeRequest;
import com.frequency.bank.dtos.EmployeeDto;
import com.frequency.bank.entities.RoleType;
import com.frequency.bank.mappers.EmployeeMapper;
import com.frequency.bank.repositories.BranchRepository;
import com.frequency.bank.repositories.EmployeeRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeService {
	
	private final EmployeeRepository employeeRepository;
	private final EmployeeMapper employeeMapper;
	private final BranchRepository branchRepository;
	
	public Iterable<EmployeeDto> getAllEmployees(){
		return employeeRepository.findAll().
				stream()
				.map(employeeMapper::toDto).toList();
	}
	
	public EmployeeDto getEmployee(UUID employeeId) {
		var employeeDto = employeeMapper.toDto(employeeRepository.findById(employeeId).orElseThrow());
		return employeeDto;
	}
	
	public void addEmployee(AddEmployeeRequest request, UUID branchId) {
		var branch = branchRepository.findById(branchId).orElseThrow();
		var employee = employeeMapper.toEntity(request);
		employee.setBranch(branch);
		if (employee.getRole() == RoleType.MANAGER && branch.getBankManager() ==null) {
			branch.setBankManager(employee);
		}else if(employee.getRole() == RoleType.MANAGER && branch.getBankManager() !=null) {
			branch.getBankManager().setRole(RoleType.EMPLOYEE);
			branch.setBankManager(employee);
		}
		
		employeeRepository.save(employee);
		branchRepository.save(branch);
	}
	
	public void deleteEmployee(UUID employeeId) {
		var employee = employeeRepository.findById(employeeId).orElseThrow();
		employeeRepository.delete(employee);
	}

}
