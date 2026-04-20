package com.example.inventariocqrs.command.handler;

import com.example.inventariocqrs.command.EliminarProductoCommand;
import com.example.inventariocqrs.command.repository.ProductoWriteRepository;
import com.example.inventariocqrs.domain.ProductoId;
import com.example.inventariocqrs.domain.ProductoNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class EliminarProductoHandler {

    private final ProductoWriteRepository writeRepo;

    public EliminarProductoHandler(ProductoWriteRepository writeRepo) {
        this.writeRepo = writeRepo;
    }

    public void handle(EliminarProductoCommand cmd) {
        ProductoId id = new ProductoId(cmd.productoId());
        writeRepo.buscarPorId(id)
                .orElseThrow(() -> new ProductoNotFoundException(cmd.productoId()));
        writeRepo.eliminar(id);
    }
}