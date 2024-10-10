package count.bank.Mapper;

import count.bank.Dto.RolesDto.RolesDto;
import count.bank.Entity.Permission;
import count.bank.Entity.Roles;
import count.bank.Repository.RepoPermission;

import java.util.Set;
import java.util.stream.Collectors;

public class RolMapper {

    public RolMapper(RepoPermission repoPermission) {
    }

    public static Roles toRoles(RolesDto rolesDto, Set<Permission> permissions){
        return Roles.builder()
                .enumRole(rolesDto.getEnumRole())
                .permissions(permissions)
                .build();
    }

    public static RolesDto toRolesDto(Roles roles){
        return RolesDto.builder()
                .enumRole(roles.getEnumRole())
                .permissionId(roles.getPermissions().stream().map(
                        permission -> permission.getId()).collect(Collectors.toSet()))
                .build();
    }
}
