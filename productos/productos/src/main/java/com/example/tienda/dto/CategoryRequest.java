package com.example.tienda.dto;
import jakarta.validation.constraints.NotBlank;
public record CategoryRequest(
    @NotBlank(message = "el nombre de la categoria no puede estar vacio")
    String name
) {}
