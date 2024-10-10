package count.bank.Repository;

import count.bank.Entity.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface RepoCustomer extends JpaRepository<Customers, Long> {
    Optional<Customers> findByEmail(String emial);
}
