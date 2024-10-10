package count.bank.Controller;

import count.bank.Dto.AccountsDto.AccountDto;
import count.bank.Entity.Customers;
import count.bank.Mapper.AccountMapper;
import count.bank.Repository.RepositorioAccount;
import count.bank.Service.ServiceCustomer.ServiceCustomers;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private RepositorioAccount repositorioAccount;

    @Autowired
    private ServiceCustomers serviceCustomers;

    @PostMapping("/save")
    public ResponseEntity saveAccount(@Valid @RequestBody AccountDto accountDto){
        Customers customers = serviceCustomers.getCustomersById(accountDto.getCustomerId());
         repositorioAccount.save(AccountMapper.toAccount(accountDto,customers));
         return ResponseEntity.ok().body("Cuenta ha sigo registrada");
    }
}
