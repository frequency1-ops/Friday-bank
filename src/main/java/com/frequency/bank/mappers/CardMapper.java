package com.frequency.bank.mappers;

import org.mapstruct.Mapper;

import com.frequency.bank.dtos.CardDto;
import com.frequency.bank.dtos.CreateCardRequest;
import com.frequency.bank.entities.Card;

@Mapper(componentModel = "spring")
public interface CardMapper {
	
	CardDto toDto(Card card);
	Card toEntity(CreateCardRequest request);

}
