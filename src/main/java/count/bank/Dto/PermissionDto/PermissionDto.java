package count.bank.Dto.PermissionDto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PermissionDto {

    @NotBlank(message = "El campo permiso no puede ir en blanco")
    private String name;
}
