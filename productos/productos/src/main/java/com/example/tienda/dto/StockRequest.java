package com.example.tienda.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class StockRequest {
    @NotNull (message = "la cantidad no puede ser nula")
    @Min(value= 1, message = "la cantidad debe ser mayor a 0")
    private Integer quantity;
    @NotBlank (message = "la palabra clave es requerida")
    private String operation;

    public StockRequest() {
    }

    public StockRequest(String operation, Integer quantity) {
        this.operation = operation;
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
