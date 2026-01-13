package com.frequency.bank.mappers;

import com.frequency.bank.dtos.BranchDto;
import com.frequency.bank.dtos.CreateBranchRequest;
import com.frequency.bank.entities.Branch;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-12T19:44:54+0300",
    comments = "version: 1.6.2, compiler: Eclipse JDT (IDE) 3.43.50.v20250916-1548, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class BranchMapperImpl implements BranchMapper {

    @Override
    public BranchDto toDto(Branch branch) {
        if ( branch == null ) {
            return null;
        }

        BranchDto branchDto = new BranchDto();

        branchDto.setAddress( branch.getAddress() );
        branchDto.setBranchId( branch.getBranchId() );
        branchDto.setBranchName( branch.getBranchName() );
        branchDto.setCity( branch.getCity() );

        return branchDto;
    }

    @Override
    public Branch toEntity(CreateBranchRequest request) {
        if ( request == null ) {
            return null;
        }

        Branch branch = new Branch();

        branch.setAddress( request.getAddress() );
        branch.setBranchName( request.getBranchName() );
        branch.setCity( request.getCity() );

        return branch;
    }
}
