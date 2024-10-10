package count.bank.Repository;

import count.bank.Entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioAccount extends JpaRepository<Accounts, Long> {
}
