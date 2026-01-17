package com.frequency.bank.controllers;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frequency.bank.dtos.AddEmployeeRequest;
import com.frequency.bank.dtos.EmployeeDto;
import com.frequency.bank.entities.RoleType;
import com.frequency.bank.mappers.EmployeeMapper;
import com.frequency.bank.repositories.BranchRepository;
import com.frequency.bank.repositories.EmployeeRepository;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {
	
	private final EmployeeRepository employeeRepository;
	private final EmployeeMapper employeeMapper;
	private final BranchRepository branchRepository;
	
	
	@GetMapping
	public ResponseEntity<Iterable<EmployeeDto>> getAllEmployess(){
		return ResponseEntity.ok(employeeRepository.findAll().
				stream()
				.map(employeeMapper::toDto).toList());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDto> getEmployee(
				@PathVariable(name = "id") UUID employeeId	
			){
		var employeeDto = employeeMapper.toDto(employeeRepository.findById(employeeId).orElseThrow());
		return ResponseEntity.ok(employeeDto);
	}
	
	@PostMapping("/{id}/add-employee")
	public ResponseEntity<Void> addEmployee(
			@RequestBody AddEmployeeRequest request,
			@PathVariable(name = "id") UUID branchId
			){
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
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEmployee(
				
				@PathVariable(name="id") UUID employeeId
			){
		var employee = employeeRepository.findById(employeeId).orElseThrow();
		employeeRepository.delete(employee);
		return ResponseEntity.noContent().build();
	}


}
