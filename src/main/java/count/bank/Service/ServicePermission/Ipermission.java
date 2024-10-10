package count.bank.Service.ServicePermission;

import count.bank.Dto.PermissionDto.PermissionDto;
import count.bank.Entity.Permission;
import count.bank.Entity.Roles;

import java.util.Set;

public interface Ipermission {

   public void save(PermissionDto permissionDto);
   Set<Permission> getPermissionByIds(Set<Long> permissionIds);
}
