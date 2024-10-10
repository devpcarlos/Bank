package count.bank.Service.ServicePermission;

import count.bank.Dto.PermissionDto.PermissionDto;
import count.bank.Entity.Permission;
import count.bank.Mapper.PermissionMapper;
import count.bank.Repository.RepoPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ServicePermission implements Ipermission{

    @Autowired
    private RepoPermission repoPermission;
    @Override
    public void save(PermissionDto permissionDto) {
        repoPermission.save(PermissionMapper.toPermission(permissionDto));

    }
    @Override
    public Set<Permission>  getPermissionByIds(Set<Long> permissionIds) {
        return permissionIds.stream()
                .map(id->repoPermission.findById(id)
                        .orElseThrow(()->new NoSuchElementException("Permiso no encontrado: "+id)))
                .collect(Collectors.toSet());
    }
}
