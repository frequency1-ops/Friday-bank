package com.frequency.bank.dtos;

import com.frequency.bank.entities.CardType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCardRequest {
	
	private CardType cardType;

}
