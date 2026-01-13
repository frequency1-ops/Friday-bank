package com.frequency.bank.mappers;

import com.frequency.bank.dtos.AddEmployeeRequest;
import com.frequency.bank.dtos.EmployeeDto;
import com.frequency.bank.entities.Employee;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-11T22:37:57+0300",
    comments = "version: 1.6.2, compiler: Eclipse JDT (IDE) 3.43.50.v20250916-1548, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public EmployeeDto toDto(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeDto employeeDto = new EmployeeDto();

        employeeDto.setEmployeeId( employee.getEmployeeId() );
        employeeDto.setFirstName( employee.getFirstName() );
        employeeDto.setLastName( employee.getLastName() );
        employeeDto.setRole( employee.getRole() );

        employeeDto.setBranchName( employee.getBranch().getBranchName() );

        return employeeDto;
    }

    @Override
    public Employee toEntity(AddEmployeeRequest request) {
        if ( request == null ) {
            return null;
        }

        Employee employee = new Employee();

        employee.setFirstName( request.getFirstName() );
        employee.setLastName( request.getLastName() );
        employee.setRole( request.getRole() );
        employee.setSalary( request.getSalary() );

        return employee;
    }
}
