package com.frequency.bank.mappers;

import com.frequency.bank.dtos.TransactionDto;
import com.frequency.bank.dtos.TransactionRequest;
import com.frequency.bank.entities.Transaction;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-18T21:36:23+0300",
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

        return transactionDto;
    }

    @Override
    public TransactionRequest toEntity(TransactionRequest request) {
        if ( request == null ) {
            return null;
        }

        TransactionRequest transactionRequest = new TransactionRequest();

        return transactionRequest;
    }
}
