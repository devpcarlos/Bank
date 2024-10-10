package count.bank.Mapper;

import count.bank.Dto.AccountsDto.AccountDto;
import count.bank.Entity.Accounts;
import count.bank.Entity.Customers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AccountMapper {
    public static Accounts toAccount(AccountDto accountDto, Customers customerId){
      return accountDto== null ? null :
        Accounts.builder()
                .type_account(accountDto.getType_account())
                .current_balance(accountDto.getCurrent_balance())
                .customer(customerId)
                .build();
    }

    public static AccountDto toAccountDto(Accounts account){
        return account== null ? null :
                AccountDto.builder()
                .type_account(account.getType_account())
                .current_balance(account.getCurrent_balance())
                .customerId(account.getCustomer().getId())
                .build();
    }
//
//    public static Accounts UpdateToAccount(Accounts accounts, Accounts UpdateAccounts){
//        accounts.setType_account(UpdateAccounts.getType_account()==null ? accounts.getType_account() : UpdateAccounts.getType_account());
//        accounts.setCurrent_balance(UpdateAccounts.getCurrent_balance()==null ? accounts.getCurrent_balance() : UpdateAccounts.getCurrent_balance());
//        return accounts;
//    }

    public static Accounts UpdateToAccounts(AccountDto accountDto, Accounts accounts){
        if (accountDto == null || accounts == null) {
            return null;
        }
        accounts.setType_account(accountDto.getType_account()==null ? accounts.getType_account() : accountDto.getType_account());
        accounts.setCurrent_balance(accountDto.getCurrent_balance()==null ? accounts.getCurrent_balance() : accountDto.getCurrent_balance());
        return accounts;
    }

//    public static List<Accounts>toAccountList(List<AccountDto> accountDto){
//        return accountDto.stream().map(a ->toAccount(a)).collect(Collectors.toList());
//    }

    public static List<AccountDto>accountDtoList(List<Accounts>accounts){
        return accounts.stream().map(accounts1 ->toAccountDto(accounts1)).collect(Collectors.toList());
    }

}
