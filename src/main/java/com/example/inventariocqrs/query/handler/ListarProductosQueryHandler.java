package com.example.inventariocqrs.query.handler;

import com.example.inventariocqrs.query.ListarProductosQuery;
import com.example.inventariocqrs.query.model.ProductoView;
import com.example.inventariocqrs.query.repository.ProductoReadRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ListarProductosQueryHandler {

    private final ProductoReadRepository readRepo;

    public ListarProductosQueryHandler(ProductoReadRepository readRepo) {
        this.readRepo = readRepo;
    }

    public List<ProductoView> handle(ListarProductosQuery query) {
        return readRepo.buscarTodos().stream()
                .filter(p -> query.soloDisponibles() ? p.stockDisponible() > 0 : true)
                .collect(Collectors.toList());
    }
}