package com.example.inventariocqrs.query.handler;

import com.example.inventariocqrs.domain.ProductoNotFoundException;
import com.example.inventariocqrs.query.BuscarProductoQuery;
import com.example.inventariocqrs.query.model.ProductoView;
import com.example.inventariocqrs.query.repository.ProductoReadRepository;
import org.springframework.stereotype.Component;

@Component
public class BuscarProductoQueryHandler {

    private final ProductoReadRepository readRepo;

    public BuscarProductoQueryHandler(ProductoReadRepository readRepo) {
        this.readRepo = readRepo;
    }

    public ProductoView handle(BuscarProductoQuery query) {
        return readRepo.buscarPorId(query.productoId())
                .orElseThrow(() -> new ProductoNotFoundException(query.productoId()));
    }
}