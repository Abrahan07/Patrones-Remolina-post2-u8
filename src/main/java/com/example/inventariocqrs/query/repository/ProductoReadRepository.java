package com.example.inventariocqrs.query.repository;

import com.example.inventariocqrs.query.model.ProductoView;

import java.util.List;
import java.util.Optional;

public interface ProductoReadRepository {
    List<ProductoView> buscarTodos();
    Optional<ProductoView> buscarPorId(String id);
}