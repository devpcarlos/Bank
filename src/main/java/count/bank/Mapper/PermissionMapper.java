package count.bank.Mapper;

import count.bank.Dto.PermissionDto.PermissionDto;
import count.bank.Entity.Permission;

public class PermissionMapper {

    public static Permission toPermission(PermissionDto permissionDto){
        return Permission.builder()
                .name(permissionDto.getName())
                .build();
    }

    public static PermissionDto toPermissionDto(Permission permission){
        return PermissionDto.builder()
                .name(permission.getName())
                .build();
    }
}
