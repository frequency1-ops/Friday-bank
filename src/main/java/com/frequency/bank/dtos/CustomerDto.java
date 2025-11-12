package com.frequency.bank.dtos;


import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {
	private UUID customerId;
	private String firstName;
	private String lastName;
	private String email;
}
