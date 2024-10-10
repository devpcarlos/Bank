package count.bank.Service.ServiceCustomer;

import count.bank.Dto.CustomerDto.CustomerDto;
import count.bank.Entity.Customers;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

public interface ICustomer {
    public void save (CustomerDto customerDto);
    public void update(Long id, CustomerDto customers);
    public List<Customers>findCustomers();
    Customers getCustomersById(Long CustomerId);
    public void delete(Long id);
}
