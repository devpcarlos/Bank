package count.bank.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity(name = "account")
public class Accounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String type_account;
    private BigDecimal current_balance;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customers customer;
    @OneToMany(mappedBy = "account")
    private List<Transactions> transactions;
}
