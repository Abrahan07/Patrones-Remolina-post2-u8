package com.example.inventariocqrs.infrastructure.persistence;

import com.example.inventariocqrs.query.model.ProductoView;
import com.example.inventariocqrs.query.repository.ProductoReadRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ProductoReadRepositoryImpl implements ProductoReadRepository {

    private final ProductoJpaRepository jpaRepository;

    public ProductoReadRepositoryImpl(ProductoJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<ProductoView> buscarTodos() {
        return jpaRepository.findAll().stream()
                .map(e -> new ProductoView(
                        e.getId(),
                        e.getNombre(),
                        e.getCategoria(),
                        e.getPrecioUnitario(),
                        e.getStockDisponible(),
                        ProductoView.calcularEstado(e.getStockDisponible())
                ))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductoView> buscarPorId(String id) {
        return jpaRepository.findById(id)
                .map(e -> new ProductoView(
                        e.getId(),
                        e.getNombre(),
                        e.getCategoria(),
                        e.getPrecioUnitario(),
                        e.getStockDisponible(),
                        ProductoView.calcularEstado(e.getStockDisponible())
                ));
    }
}