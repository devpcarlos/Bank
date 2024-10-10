package count.bank.Controller;

import count.bank.Dto.CustomerDto.CustomerDto;
import count.bank.Service.ServiceCustomer.ServiceCustomers;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private ServiceCustomers serviceCustomers;

    @PostMapping("/save")
    public ResponseEntity createCustomer(@Valid @RequestBody CustomerDto customerDto){
        serviceCustomers.save(customerDto);
        return ResponseEntity.ok().body("El cliente ha sido creado con exito") ;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCustomer(@PathVariable Long id, @Valid @RequestBody CustomerDto customerDto)
    {
        serviceCustomers.update(id,customerDto);
      return ResponseEntity.ok().body("El cliente ha sido actualizado con exito") ;

    }
}
