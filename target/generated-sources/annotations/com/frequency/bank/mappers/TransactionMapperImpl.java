package com.frequency.bank.mappers;

import com.frequency.bank.dtos.TransactionDto;
import com.frequency.bank.dtos.TransactionRequest;
import com.frequency.bank.entities.Transaction;
import java.time.LocalDate;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-25T13:18:59+0300",
    comments = "version: 1.6.2, compiler: Eclipse JDT (IDE) 3.44.0.v20251118-1623, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class TransactionMapperImpl implements TransactionMapper {

    @Override
    public TransactionDto toDto(Transaction transaction) {
        if ( transaction == null ) {
            return null;
        }

        TransactionDto transactionDto = new TransactionDto();

        transactionDto.setAmount( transaction.getAmount() );
        transactionDto.setDescription( transaction.getDescription() );
        if ( transaction.getTimeStamp() != null ) {
            transactionDto.setTimeStamp( transaction.getTimeStamp().toLocalDate() );
        }
        transactionDto.setTransactionId( transaction.getTransactionId() );

        return transactionDto;
    }

    @Override
    public Transaction toEntity(TransactionRequest request) {
        if ( request == null ) {
            return null;
        }

        Transaction transaction = new Transaction();

        transaction.setAmount( request.getAmount() );
        transaction.setDescription( request.getDescription() );
        transaction.setType( request.getType() );

        return transaction;
    }
}
