package count.bank.Dto.RolesDto;

import count.bank.Dto.PermissionDto.PermissionDto;
import count.bank.Entity.Enum.EnumRole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
public class RolesDto {

    @NotNull(message = "El campo rol no puede ir en blanco")
    @Enumerated(EnumType.STRING)
    private EnumRole enumRole;
    private Set<Long> permissionId;

}
