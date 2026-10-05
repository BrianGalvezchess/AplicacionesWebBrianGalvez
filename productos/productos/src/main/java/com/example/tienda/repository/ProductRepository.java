package com.example.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tienda.productos.Product;

//clase generica(interfaz generica) que va a tener todos los metodos de CRUD, para poder hacer operaciones con la base de datos
//primero empezamos con la base de datos junto con la conexion y despues al reposiroy, despues al service y al ultimo al controllador
public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
