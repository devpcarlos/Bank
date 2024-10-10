package count.bank.Service.ServiceRole;

import count.bank.Dto.RolesDto.RolesDto;
import count.bank.Entity.Permission;
import count.bank.Entity.Roles;
import count.bank.Mapper.RolMapper;
import count.bank.Repository.RepoRole;
import count.bank.Service.ServicePermission.ServicePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ServiceRol implements IRol {

    @Autowired
    private RepoRole repoRole;

    @Autowired
    private ServicePermission servicePermission;
    @Override
    public void save(RolesDto rolesDto) {
        Set<Permission> permissions = servicePermission.getPermissionByIds(rolesDto.getPermissionId());
        Roles roles = RolMapper.toRoles(rolesDto,permissions);
        repoRole.save(roles);
    }

    @Override
    public Set<Roles> getRolesByIds(Set<Long> roleIds) {
        return roleIds.stream().map(
                id->repoRole.findById(id).orElseThrow(()-> new NoSuchElementException("El rol no encontrado: "+id))
        ).collect(Collectors.toSet());
    }
}
