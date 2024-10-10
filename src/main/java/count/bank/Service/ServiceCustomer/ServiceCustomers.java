package count.bank.Service.ServiceCustomer;

import count.bank.Dto.CustomerDto.CustomerDto;
import count.bank.Entity.Customers;
import count.bank.Entity.Roles;
import count.bank.Mapper.CustomerMap;
import count.bank.Repository.RepoCustomer;
import count.bank.Service.ServiceRole.ServiceRol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ServiceCustomers implements ICustomer {

    @Autowired
    private RepoCustomer repoCustomer;

    @Autowired
    private ServiceRol serviceRol;
    @Override
    public void save(CustomerDto customerDto) {
        Set<Roles> roles = serviceRol.getRolesByIds(customerDto.getRolesId());
        Customers customers=CustomerMap.toCustomer(customerDto, roles);
     repoCustomer.save(customers);
    }

    @Override
    public void update(Long id, CustomerDto customerDto) {
        Customers customers = repoCustomer.findById(id).orElseThrow(()->new NoSuchElementException("Cliente con ID " + id + " no encontrado"));
        repoCustomer.save(CustomerMap.toUpdateCustomers(customerDto, customers));
    }

    @Override
    public List<Customers> findCustomers() {
        return repoCustomer.findAll();
    }

    @Override
    public Customers getCustomersById(Long CustomerId) {
        return repoCustomer.findById(CustomerId).orElseThrow(() -> new NoSuchElementException("Cliente no encontrado"));
    }

    @Override
    public void delete(Long id) {
        repoCustomer.deleteById(id);
    }
}
