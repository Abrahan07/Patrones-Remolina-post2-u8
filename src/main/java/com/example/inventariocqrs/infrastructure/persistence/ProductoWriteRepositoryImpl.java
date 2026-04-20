package com.example.inventariocqrs.infrastructure.persistence;

import com.example.inventariocqrs.command.repository.ProductoWriteRepository;
import com.example.inventariocqrs.domain.Producto;
import com.example.inventariocqrs.domain.ProductoId;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ProductoWriteRepositoryImpl implements ProductoWriteRepository {

    private final ProductoJpaRepository jpaRepository;

    public ProductoWriteRepositoryImpl(ProductoJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void guardar(Producto producto) {
        ProductoEntity entity = new ProductoEntity(
                producto.getId().getValor(),
                producto.getNombre(),
                producto.getCategoria(),
                producto.getPrecioUnitario(),
                producto.getStockDisponible()
        );
        jpaRepository.save(entity);
    }

    @Override
    public Optional<Producto> buscarPorId(ProductoId id) {
        return jpaRepository.findById(id.getValor())
                .map(e -> new Producto(
                        new ProductoId(e.getId()),
                        e.getNombre(),
                        e.getCategoria(),
                        e.getPrecioUnitario(),
                        e.getStockDisponible()
                ));
    }

    @Override
    public void eliminar(ProductoId id) {
        jpaRepository.deleteById(id.getValor());
    }
}