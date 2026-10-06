package com.example.tienda.productos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.tienda.productos.Product;
import com.example.tienda.repository.ProductRepository;

@Service 
//en una interfaz pueden ir metodos y atributos y en una clase abstracta puedes tener cierto codigo, implementacion de codigo
//uno se implementa en la interfaz y la otra se extiende en la clase abstracta, pero en este caso no es necesario porque no vamos a tener metodos ni atributos
public class ProductService {
    //repo (jpa repository) es una interfaz generica,
    //estamos usando tambien inyecciones de dependencia
    private ProductRepository repo;

    //inyeccion de dependecia
    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }
    
    //JPA SE QUEDA CORTO POR EL TEMA DE LAS CONSULTAS, POR ESO SE USA EL JDBC
    public List<Product> getAll(){
        return repo.findAll();
    }

    //optional evita que se rompa la aplicacion si no encuentra el id, en vez de eso devuelve un objeto vacio
    //puedes encontrar un producto o no, si no te devuelve un objecto de tipo opcional
    public Optional<Product> getById(Long id){
        return repo.findById(id);
    }

    //trabajmso con nuestro modelado de base de datos
    public Product save(Product product){
        return repo.save(product);
    }

    //opcional.of se convertirlo opcional a lo que guarda, regresa un objecto o mas bien una entidad y esa entidad devuelve un optional
    public Optional<Product> update(Long id, Product product){
        Optional<Product> optional = repo.findById(id);
        if(optional.isEmpty()){
            return optional.empty();
        }
        Product productDb = optional.get();

        productDb.setName(product.getName());
        productDb.setPrice(product.getPrice());
        productDb.setStock(product.getStock());

        return Optional.of(repo.save(productDb));
    }

    public boolean delete(Long id){
        if(!repo.existsById(id)){
            return false;
        }
        repo.deleteById(id);
        return true;
    }
}
