package com.frequency.bank.mappers;

import com.frequency.bank.dtos.CustomerDto;
import com.frequency.bank.dtos.RegisterCustomerRequest;
import com.frequency.bank.dtos.UpdateCustomerRequest;
import com.frequency.bank.entities.Customer;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-26T12:33:46+0300",
    comments = "version: 1.6.2, compiler: Eclipse JDT (IDE) 3.43.50.v20250916-1548, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerDto toDto(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerDto customerDto = new CustomerDto();

        customerDto.setCustomerId( customer.getCustomerId() );
        customerDto.setEmail( customer.getEmail() );
        customerDto.setFirstName( customer.getFirstName() );
        customerDto.setLastName( customer.getLastName() );

        return customerDto;
    }

    @Override
    public Customer toEntity(RegisterCustomerRequest registerCustomerRequest) {
        if ( registerCustomerRequest == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setAddress( registerCustomerRequest.getAddress() );
        customer.setDateOfBirth( registerCustomerRequest.getDateOfBirth() );
        customer.setEmail( registerCustomerRequest.getEmail() );
        customer.setFirstName( registerCustomerRequest.getFirstName() );
        customer.setLastName( registerCustomerRequest.getLastName() );
        customer.setPassword( registerCustomerRequest.getPassword() );
        customer.setPhone( registerCustomerRequest.getPhone() );

        return customer;
    }

    @Override
    public void updateCustomer(UpdateCustomerRequest request, Customer customer) {
        if ( request == null ) {
            return;
        }

        if ( request.getAddress() != null ) {
            customer.setAddress( request.getAddress() );
        }
        if ( request.getEmail() != null ) {
            customer.setEmail( request.getEmail() );
        }
        if ( request.getPhone() != null ) {
            customer.setPhone( request.getPhone() );
        }
    }
}
