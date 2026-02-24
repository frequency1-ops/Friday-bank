package com.frequency.bank.service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.frequency.bank.repositories.CustomerRepository;
import com.frequency.bank.repositories.EmployeeRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserService implements UserDetailsService{
	
	private final CustomerRepository customerRepository;
	private final EmployeeRepository employeeRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		var customer = customerRepository.findByEmail(email);
		
		if(customer.isPresent()) {
			return new User(
					customer.get().getEmail()
					, customer.get().getPassword(), Collections.emptyList());
		}
		
		var employee = employeeRepository.findByEmail(email);
		if(employee.isPresent()) {
			return new User(
						employee.get().getEmail(),
						employee.get().getPassword(),
						Collections.emptyList()
					);
		}
		
		throw new UsernameNotFoundException("User Not found");
	}

}
