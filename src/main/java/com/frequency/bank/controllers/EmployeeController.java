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
import com.frequency.bank.dtos.CustomerDto;
import com.frequency.bank.dtos.EmployeeDto;
import com.frequency.bank.entities.RoleType;
import com.frequency.bank.mappers.EmployeeMapper;
import com.frequency.bank.repositories.BranchRepository;
import com.frequency.bank.repositories.EmployeeRepository;
import com.frequency.bank.service.EmployeeService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {
	
	private final EmployeeService employeeService;
	
	
	@GetMapping
	public ResponseEntity<Iterable<EmployeeDto>> getAllEmployess(){
		return ResponseEntity.ok(employeeService.getAllEmployees());
	}
	
	@GetMapping("/{employee-id}")
	public ResponseEntity<EmployeeDto> getEmployee(
				@PathVariable(name = "employee-id") UUID employeeId	
			){
		
		var employeeDto = employeeService.getEmployee(employeeId);
		
		return ResponseEntity.ok(employeeDto);
	}
	
	@PostMapping("/{branch-id}/add-employee")
	public ResponseEntity<Void> addEmployee(
			@RequestBody AddEmployeeRequest request,
			@PathVariable(name = "branch-id") UUID branchId
			){
			employeeService.addEmployee(request, branchId);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{employee-id}")
	public ResponseEntity<Void> deleteEmployee(
				
				@PathVariable(name="employee-id") UUID employeeId
			){
			employeeService.deleteEmployee(employeeId);
		return ResponseEntity.noContent().build();
	}


}
