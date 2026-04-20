package com.example.inventariocqrs.command.handler;

import com.example.inventariocqrs.command.ActualizarStockCommand;
import com.example.inventariocqrs.command.repository.ProductoWriteRepository;
import com.example.inventariocqrs.domain.Producto;
import com.example.inventariocqrs.domain.ProductoId;
import com.example.inventariocqrs.domain.ProductoNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class ActualizarStockHandler {

    private final ProductoWriteRepository writeRepo;

    public ActualizarStockHandler(ProductoWriteRepository writeRepo) {
        this.writeRepo = writeRepo;
    }

    public String handle(ActualizarStockCommand cmd) {
        Producto producto = writeRepo
                .buscarPorId(new ProductoId(cmd.productoId()))
                .orElseThrow(() -> new ProductoNotFoundException(cmd.productoId()));

        if (cmd.delta() > 0) producto.incrementarStock(cmd.delta());
        else producto.reducirStock(Math.abs(cmd.delta()));

        writeRepo.guardar(producto);
        return "Stock actualizado. Nuevo stock: " + producto.getStockDisponible();
    }
}