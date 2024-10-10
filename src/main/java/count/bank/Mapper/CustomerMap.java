package count.bank.Mapper;

import count.bank.Dto.CustomerDto.CustomerDto;
import count.bank.Entity.Customers;
import count.bank.Entity.Roles;

import java.util.Set;
import java.util.stream.Collectors;


public class CustomerMap {

    // Converting entity object into dto object
    public static CustomerDto toCustomerDto (Customers customers) {
        return CustomerDto.builder()
                .name(customers.getName())
                .surname(customers.getSurname())
                .email(customers.getEmail())
                .password(customers.getPassword())
                .cell(customers.getCell())
                .rolesId(customers.getRoles().stream().map(
                        roles -> roles.getId()).collect(Collectors.toSet()))
                .build();
    }

   //Converting dto object into entity object
    public static Customers toCustomer (CustomerDto customerDto, Set<Roles> rolesId ) {
        return Customers.builder()
                .name(customerDto.getName())
                .surname(customerDto.getSurname())
                .email(customerDto.getEmail())
                .password(customerDto.getPassword())
                .cell(customerDto.getCell())
                .roles(rolesId)
                .build();
    }

    public static Customers toUpdateCustomersxx(Customers customers, Customers UpdateCustomers){
        customers.setName(UpdateCustomers.getName() == null ? customers.getName() : UpdateCustomers.getName());
        customers.setSurname(UpdateCustomers.getSurname() == null ? customers.getSurname() : UpdateCustomers.getSurname());
        customers.setCell(UpdateCustomers.getCell() == null ? customers.getCell() : UpdateCustomers.getCell());
        return customers;
    }

    public static Customers toUpdateCustomers(CustomerDto customerDto, Customers customers){
        customers.setName(customerDto.getName() == null ? customers.getName() : customerDto.getName());
        customers.setSurname(customerDto.getSurname() == null ? customers.getSurname() : customerDto.getSurname());
        customers.setEmail(customerDto.getEmail() == null ? customers.getEmail() : customerDto.getEmail());
        customers.setPassword(customerDto.getPassword() == null ? customers.getPassword() : customerDto.getPassword());
        customers.setCell(customerDto.getCell() == null ? customers.getCell() : customerDto.getCell());
        return customers;
    }
}
