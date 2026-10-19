package com.example.tienda.exception;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    //un mapa es un objecto,tiene un identificador y un valor, identificador izquiera y derecha valor
    ///mapeando los errores de las excepciones por default de la etiqueta @excepcion
    //sacar los errores y lo estamos metiendo a este mapa a traves de un string

    @ExceptionHandler(StockConflictException.class)
    public ResponseEntity<String> handleStockConflict(StockConflictException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }


@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<Map<String, String>> handleValidation(
        MethodArgumentNotValidException ex) {

    Map<String, String> errors = ex.getBindingResult()
        .getFieldErrors()
        .stream()
        .collect(Collectors.toMap(
            error -> error.getField(),
            error -> error.getDefaultMessage(),
            (first, second) -> first
        ));

    return ResponseEntity
        .badRequest()
        .body(errors);

    }
}
