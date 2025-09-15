package br.com.alura.comex.exception;

import br.com.alura.comex.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "br.com.alura.comex.controller")
public class GlobalExceptionHandler {

    @ExceptionHandler(CategoriaJaExisteException.class)
    public ResponseEntity<ApiResponse<Void>> handleCategoriaJaExiste(CategoriaJaExisteException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ApiResponse.error(ex.getMessage()));
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<ApiResponse<Void>> handleEntidadeNaoEncontrada(EntidadeNaoEncontradaException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidationErrors(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error("Erro de validação: " + message));
    }

    // Removido o handler genérico para não interferir com Swagger
}