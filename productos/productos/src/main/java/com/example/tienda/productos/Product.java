package com.example.tienda.productos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

//va a hacer una clase que voy a mapear en mi base de datos, por eso se pone la anotación @Entity
@Entity
@Table(name = "products") // Especifica explícitamente el nombre de la tabla
public class Product {
    //va a ser la llave primaria de mi tabla, por eso se pone la anotación @Id
    //va a ser autoincremental, por eso se pone la anotación @GeneratedValue

    @Id 
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int stock;
    private Double price;
    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    public Product(Category category, Long id, String name, Double price, int stock) {
        this.category = category;
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
    public Product() {
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    
    
    
}
