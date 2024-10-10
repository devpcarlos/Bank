package count.bank.Service.ServiceRole;

import count.bank.Dto.RolesDto.RolesDto;
import count.bank.Entity.Roles;
import org.springframework.stereotype.Component;

import java.util.Set;

public interface IRol {
    void save (RolesDto rolesDto);

    Set<Roles> getRolesByIds(Set<Long> roleIds);
}
