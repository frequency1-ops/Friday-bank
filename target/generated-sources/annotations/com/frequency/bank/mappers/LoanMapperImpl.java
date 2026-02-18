package com.frequency.bank.mappers;

import com.frequency.bank.dtos.LoanApplicationRequest;
import com.frequency.bank.dtos.LoanDto;
import com.frequency.bank.entities.Loan;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-18T21:36:23+0300",
    comments = "version: 1.6.2, compiler: Eclipse JDT (IDE) 3.44.0.v20251118-1623, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class LoanMapperImpl implements LoanMapper {

    @Override
    public LoanDto toDto(Loan loan) {
        if ( loan == null ) {
            return null;
        }

        LoanDto loanDto = new LoanDto();

        loanDto.setAmount( loan.getAmount() );
        loanDto.setEndDate( loan.getEndDate() );
        loanDto.setInterestRate( loan.getInterestRate() );
        loanDto.setLoanId( loan.getLoanId() );
        loanDto.setStartDate( loan.getStartDate() );
        loanDto.setStatus( loan.getStatus() );

        return loanDto;
    }

    @Override
    public Loan toEntity(LoanApplicationRequest request) {
        if ( request == null ) {
            return null;
        }

        Loan loan = new Loan();

        loan.setAmount( request.getAmount() );
        loan.setInterestRate( request.getInterestRate() );

        return loan;
    }
}
