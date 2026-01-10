package com.frequency.bank.controllers;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frequency.bank.dtos.EmployeeDto;
import com.frequency.bank.mappers.EmployeeMapper;
import com.frequency.bank.repositories.EmployeeRepository;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {
	
	private final EmployeeRepository employeeRepository;
	private final EmployeeMapper employeeMapper;
	
	
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
		var employeeDto = employeeMapper.toDto(employeeRepository.findByEmployeeId(employeeId));
		return ResponseEntity.ok(employeeDto);
	}


}
