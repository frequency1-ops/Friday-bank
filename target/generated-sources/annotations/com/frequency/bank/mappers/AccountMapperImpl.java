package com.frequency.bank.mappers;

import com.frequency.bank.dtos.AccountDto;
import com.frequency.bank.dtos.CreateAccountRequest;
import com.frequency.bank.entities.Account;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-15T13:39:13+0300",
    comments = "version: 1.6.2, compiler: Eclipse JDT (IDE) 3.43.50.v20250916-1548, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class AccountMapperImpl implements AccountMapper {

    @Override
    public AccountDto toDto(Account account) {
        if ( account == null ) {
            return null;
        }

        AccountDto accountDto = new AccountDto();

        accountDto.setAccountId( account.getAccountId() );
        accountDto.setAccountNumber( account.getAccountNumber() );
        accountDto.setAccountType( account.getAccountType() );

        accountDto.setCustomerId( account.getCustomer().getCustomerId() );
        accountDto.setBranchId( account.getBranch().getBranchId() );

        return accountDto;
    }

    @Override
    public Account toEntity(CreateAccountRequest request) {
        if ( request == null ) {
            return null;
        }

        Account account = new Account();

        account.setAccountType( request.getAccountType() );

        return account;
    }
}
