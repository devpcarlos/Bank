package count.bank.Controller;

import count.bank.Dto.PermissionDto.PermissionDto;
import count.bank.Service.ServicePermission.ServicePermission;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("permission")
public class PermissionController {

    @Autowired
    private ServicePermission servicePermission;

    @PostMapping("/save")
    public ResponseEntity  savePermission( @Valid @RequestBody PermissionDto permissionDto){
        servicePermission.save(permissionDto);
        return ResponseEntity.ok().body("El permiso " +permissionDto.getName()+" ha sido guardado" );

    }
}
