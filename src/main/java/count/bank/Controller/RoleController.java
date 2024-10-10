package count.bank.Controller;

import count.bank.Dto.RolesDto.RolesDto;
import count.bank.Service.ServiceRole.ServiceRol;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("role")
public class RoleController {

    @Autowired
    private ServiceRol serviceRol;

    @PostMapping("/save")
    public ResponseEntity saveRole(@Valid @RequestBody RolesDto rolesDto){
        serviceRol.save(rolesDto);
        return ResponseEntity.ok().body("Los roles han sido guardado");
    }
}
