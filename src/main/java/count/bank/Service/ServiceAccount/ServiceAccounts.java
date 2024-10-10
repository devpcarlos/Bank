package count.bank.Service.ServiceAccount;

import count.bank.Dto.AccountsDto.AccountDto;
import count.bank.Entity.Accounts;
import count.bank.Entity.Customers;
import count.bank.Mapper.AccountMapper;
import count.bank.Repository.RepositorioAccount;
import count.bank.Service.ServiceCustomer.ServiceCustomers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ServiceAccounts implements InterfaceAccount {

    @Autowired
    private RepositorioAccount repositorioAccount;

    @Autowired
    private ServiceCustomers serviceCustomers;

    @Override
    public void save(AccountDto accountDto) {
        Customers customers = serviceCustomers.getCustomersById(accountDto.getCustomerId());
        Accounts accounts = AccountMapper.toAccount(accountDto, customers);
        repositorioAccount.save(accounts);
    }

}
