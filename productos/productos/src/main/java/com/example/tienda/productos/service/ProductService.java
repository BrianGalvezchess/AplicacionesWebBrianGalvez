package com.example.tienda.productos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.tienda.exception.ResourceNotFoundException;
import com.example.tienda.productos.Category;
import com.example.tienda.productos.Product;
import com.example.tienda.repository.CategoryRepository;
import com.example.tienda.repository.ProductRepository;

@Service 
//en una interfaz pueden ir metodos y atributos y en una clase abstracta puedes tener cierto codigo, implementacion de codigo
//uno se implementa en la interfaz y la otra se extiende en la clase abstracta, pero en este caso no es necesario porque no vamos a tener metodos ni atributos
public class ProductService {
    //repo (jpa repository) es una interfaz generica,
    //estamos usando tambien inyecciones de dependencia
    private final ProductRepository repo;
    private final CategoryRepository repoCategory;

    //inyeccion de dependecia
    public ProductService(ProductRepository repo, CategoryRepository repoCategory) {
        this.repo = repo;
        this.repoCategory = repoCategory;
    }
    
    //JPA SE QUEDA CORTO POR EL TEMA DE LAS CONSULTAS, POR ESO SE USA EL JDBC
    public List<Product> getAll(){
        return repo.findAll();
    }

    //optional evita que se rompa la aplicacion si no encuentra el id, en vez de eso devuelve un objeto vacio
    //puedes encontrar un producto o no, si no te devuelve un objecto de tipo opcional
    public Optional<Product> FindById(Long id){
        return repo.findById(id);
    }

    //trabajamos con nuestro modelado de base de datos
    //bota una excepcion en caso de que no encuentre la categoria
    //no queremso que se guarde un producto sin la categoria

    //si es presente pasa y si no pasa la excepcion
    public Product save(Product product, Long categoryId){
        Category category = repoCategory.
        findById(categoryId).
        orElseThrow(
            ()->
            new ResourceNotFoundException("Category not found with id:"
                + categoryId)
        );
        //entonces arroja un error, evitando que el producto se cree
        product.setCategory(category);
        return repo.save(product);
    }

    //sacar el id dentro de la entidad del producto o 

    //opcional.of se convertirlo opcional a lo que guarda, regresa un objecto o mas bien una entidad y esa entidad devuelve un optional
    // Actualizar producto validando que existan TANTO el producto COMO la categoría
    public Product update(Long id, Product product, Long categoryId){
        // 1. Verificar si el producto existe, si no -> excepción personalizada
        Product productDb = repo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

        // 2. Verificar si la categoría existe, si no -> excepción personalizada
        Category category = repoCategory.findById(categoryId)
            .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + categoryId));

        // 3. Actualizar campos
        productDb.setName(product.getName());
        productDb.setPrice(product.getPrice());
        productDb.setStock(product.getStock());
        productDb.setCategory(category);

        // Retornamos directamente la entidad guardada
        return repo.save(productDb);
    }

    public boolean delete(Long id){
        if(!repo.existsById(id)){
            return false;
        }
        repo.deleteById(id);
        return true;
    }
}
