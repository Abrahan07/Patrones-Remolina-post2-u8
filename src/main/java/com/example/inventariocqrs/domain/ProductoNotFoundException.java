package com.example.inventariocqrs.domain;

public class ProductoNotFoundException extends RuntimeException {
    public ProductoNotFoundException(String id) {
        super("Producto no encontrado con ID: " + id);
    }
}