package com.example.tienda.productos.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tienda.dto.ProductRequest;
import com.example.tienda.dto.ProductResponse;
import com.example.tienda.productos.Product;
import com.example.tienda.productos.service.ProductService;

import jakarta.validation.Valid;

@RestController 
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    // Obtenemos todos los productos
    @GetMapping()
    public ResponseEntity<List<ProductResponse>> findAll() {
        List<ProductResponse> listResponse = new ArrayList<>();
        for (Product product : service.getAll()) {
            listResponse.add(toResponse(product));
        }
        return ResponseEntity.ok(listResponse);
    }

    // Obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable Long id) {
        Optional<Product> product = service.FindById(id);
        if (product.isPresent()) {
            return ResponseEntity.ok(toResponse(product.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear (@Valid maneja automáticamente si el nombre viene vacío o nulo)
    @PostMapping()
    public ResponseEntity<ProductResponse> save(@Valid @RequestBody ProductRequest product) {
        Product productSaved = service.save( toEntity(product),product.categoryId());
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(productSaved));
    }

    // Eliminar un producto por su id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = service.delete(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Actualizar el producto por su id
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(@PathVariable Long id, @Valid @RequestBody ProductRequest product) {
        Optional<Product> optional = service.update(id, toEntity(product));
        if (optional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(optional.get()));
        } 
        return ResponseEntity.notFound().build();
    }

    // Mapeo a Response DTO
    private ProductResponse toResponse(Product product) {
    Long categoryId = product.getCategory() != null ? product.getCategory().getId() : null;
    String categoryName = product.getCategory() != null ? product.getCategory().getName() : "";
    
    return new ProductResponse(
        product.getId(), 
        product.getName(), 
        product.getPrice(), 
        product.getStock(), 
        categoryId, 
        categoryName
    );
}

    private Product toEntity(ProductRequest request) {
        Product product = new Product();
        product.setName(request.name());
        product.setPrice(request.price());
        product.setStock(request.stock());
        return product;
    }
}

//el proceso sera diferente tenemos que aseugrarno que exista esa ccategoria para guardar

//DESACOMPLAMIENTO
    //LAS CLASES NO ESTAN PARCIALMENTE RELACIONADAS

    //NOMBRE CON COMILLAS Y STOCK Y PRECIO CON NUMERO NEGATIVO
    //QUE NO SEAN NULLOS O MENORES A CERO

    //o si llega un atributo malo debo botar el error
    //implementar un interceptor para el mensaje personalizado

