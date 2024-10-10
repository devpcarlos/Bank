package count.bank.Repository;

import count.bank.Entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoPermission extends JpaRepository<Permission, Long> {
}
