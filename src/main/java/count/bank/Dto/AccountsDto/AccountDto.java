package count.bank.Dto.AccountsDto;

import count.bank.Dto.CustomerDto.CustomerDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@Builder
public class AccountDto {

        private String type_account;
        private BigDecimal current_balance;
        private Long customerId;
}
