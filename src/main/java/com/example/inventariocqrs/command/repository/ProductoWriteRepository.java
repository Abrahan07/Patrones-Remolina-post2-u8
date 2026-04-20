package com.example.inventariocqrs.command.repository;

import com.example.inventariocqrs.domain.Producto;
import com.example.inventariocqrs.domain.ProductoId;

import java.util.Optional;

public interface ProductoWriteRepository {
    void guardar(Producto producto);
    Optional<Producto> buscarPorId(ProductoId id);
    void eliminar(ProductoId id);
}