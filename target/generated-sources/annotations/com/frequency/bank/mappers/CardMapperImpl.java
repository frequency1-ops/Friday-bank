package com.frequency.bank.mappers;

import com.frequency.bank.dtos.CardDto;
import com.frequency.bank.dtos.CreateCardRequest;
import com.frequency.bank.entities.Card;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-25T16:07:06+0300",
    comments = "version: 1.6.2, compiler: Eclipse JDT (IDE) 3.44.0.v20251118-1623, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class CardMapperImpl implements CardMapper {

    @Override
    public CardDto toDto(Card card) {
        if ( card == null ) {
            return null;
        }

        CardDto cardDto = new CardDto();

        cardDto.setCardId( card.getCardId() );
        cardDto.setCardNumber( card.getCardNumber() );
        cardDto.setCardType( card.getCardType() );
        cardDto.setExpiryDate( card.getExpiryDate() );

        return cardDto;
    }

    @Override
    public Card toEntity(CreateCardRequest request) {
        if ( request == null ) {
            return null;
        }

        Card card = new Card();

        card.setCardType( request.getCardType() );

        return card;
    }
}
