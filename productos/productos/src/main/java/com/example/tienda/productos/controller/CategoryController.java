package com.example.tienda.productos.controller;

import java.util.ArrayList;
import java.util.List;

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

import com.example.tienda.dto.CategoryRequest;
import com.example.tienda.dto.CategoryResponse;
import com.example.tienda.exception.ResourceNotFoundException;
import com.example.tienda.productos.Category;
import com.example.tienda.repository.CategoryRepository;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/categories") 
public class CategoryController {
    private final CategoryRepository repoCategory;

    public CategoryController(CategoryRepository repoCategory) {
        this.repoCategory = repoCategory;
    }
    @GetMapping
    public ResponseEntity<List<CategoryResponse>> findAll() {
        List<CategoryResponse> listResponse = new ArrayList<>();
        for (Category category : repoCategory.findAll()) {
            listResponse.add(toResponse(category));
        }
        return ResponseEntity.ok(listResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getById(@PathVariable Long id) {
        Category category = repoCategory.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
        return ResponseEntity.ok(toResponse(category));
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> save(@Valid @RequestBody CategoryRequest request) {
        Category category = new Category();
        category.setName(request.name());
        Category saved = repoCategory.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> update(@PathVariable Long id, @Valid @RequestBody CategoryRequest request) {
        Category category = repoCategory.
            findById(id)
            .orElseThrow(() -> 
            new ResourceNotFoundException("Category not found with id: " + id));
        
        category.setName(request.name());
        Category updated = repoCategory.save(category);
        return ResponseEntity.ok(toResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repoCategory.existsById(id)) {
            throw new ResourceNotFoundException("Category not found with id: " + id);
        }
        repoCategory.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private CategoryResponse toResponse(Category category) {
        return new CategoryResponse(category.getId(), category.getName());
    }
}
