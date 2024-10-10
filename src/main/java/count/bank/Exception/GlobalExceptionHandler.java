package count.bank.Exception;

import org.aspectj.bridge.MessageUtil;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity <?>handleValidationExceptions(MethodArgumentNotValidException ex){

        ValidErrorResponse errors = new ValidErrorResponse(
                "Error de validacion",
                new Date(),
        ex.getBindingResult().getFieldError().getDefaultMessage());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> IntegrityException(DataIntegrityViolationException exception){
        ValidErrorResponse response = new ValidErrorResponse(
                "Datos duplicados",
                new Date(), exception.getMessage());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> ElementException(NoSuchElementException exception){
        ValidErrorResponse response = new ValidErrorResponse("El id no existe", new Date(), exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<?> EmailNotFound(EmailNotFoundException exception){
        ValidErrorResponse response = new ValidErrorResponse(
                "El correo no fue encontrado", new Date(),
                exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
