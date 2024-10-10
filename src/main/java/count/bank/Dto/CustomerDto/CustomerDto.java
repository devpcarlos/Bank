package count.bank.Dto.CustomerDto;

import count.bank.Dto.RolesDto.RolesDto;
import count.bank.Entity.Roles;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
public class CustomerDto {
    @NotBlank(message = "El campo nombre no puede ir vacio")
    private String name;
    @NotBlank(message = "El campo apellido no puede ir vacio")
    private String surname;
    @Email(message = "Debe ingresar un email valido")
    private String email;
    @Size(min = 5, max = 12, message = "El valor maximo es de: 12")
    @NotBlank(message = "El campo no puede ir en blanco")
    private String password;
    @NotNull(message = "El campo celular no puede ir vacio")
    private Long cell;
    private Set<Long> rolesId;
}
