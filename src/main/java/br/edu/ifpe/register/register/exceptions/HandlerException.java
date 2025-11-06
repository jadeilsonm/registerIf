package br.edu.ifpe.register.register.exceptions;

import jakarta.persistence.PersistenceException;
import org.apache.coyote.BadRequestException;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerException {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception exception) {
        return ResponseEntity.internalServerError().body("Internal server error: " + exception.getMessage());
    }

    @ExceptionHandler({
            ConfigDataResourceNotFoundException.class,
            NotFoundException.class
    })
    public ResponseEntity<String> handleResourceNotFoundException(RuntimeException exception) {
        return ResponseEntity.status(404).body("Resource not found: " + exception.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleBadRequestException(BadRequestException exception) {
        return ResponseEntity.badRequest().body("Bad request: " + exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException exception) {
        String errorMessage = exception.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return ResponseEntity.badRequest().body("Validation error: " + errorMessage);
    }

    @ExceptionHandler(PersistenceException.class)
    public ResponseEntity<String> handlePersistenceException(PersistenceException exception) {
        return ResponseEntity.status(500).body("Database error: " + exception.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
        return ResponseEntity.status(409).body("Data integrity violation: " + exception.getMessage());
    }
}
