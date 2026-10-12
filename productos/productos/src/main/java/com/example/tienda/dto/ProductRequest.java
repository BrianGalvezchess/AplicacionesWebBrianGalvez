package com.example.tienda.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record ProductRequest(
    @NotBlank(message = "El nombre no puede estar vacío")
    String name,
    @NotNull
    @Positive(message = "El precio debe ser mayor a cero")
    Double price,
    //se ocupa la etiqueta NotNull para 
    //cuando es una variable de instancia, si e sprimitivo tiene una inicializacion por default
    //pero aqui expresamos con NotNull nunca seria nullo, lo cambiamos cuando es nullo cuando tu no puedes enviar ese atributo
    @NotNull 
    @PositiveOrZero(message = "El stock debe ser mayor o igual a cero")
    Integer stock
) {

}