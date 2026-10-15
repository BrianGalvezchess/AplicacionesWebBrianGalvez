package com.example.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tienda.productos.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
