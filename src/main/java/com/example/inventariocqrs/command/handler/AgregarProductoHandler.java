package com.example.inventariocqrs.command.handler;

import com.example.inventariocqrs.command.AgregarProductoCommand;
import com.example.inventariocqrs.command.repository.ProductoWriteRepository;
import com.example.inventariocqrs.domain.Producto;
import com.example.inventariocqrs.domain.ProductoId;
import org.springframework.stereotype.Component;

@Component
public class AgregarProductoHandler {

    private final ProductoWriteRepository writeRepo;

    public AgregarProductoHandler(ProductoWriteRepository writeRepo) {
        this.writeRepo = writeRepo;
    }

    public ProductoId handle(AgregarProductoCommand cmd) {
        Producto producto = new Producto(
                ProductoId.nuevo(),
                cmd.nombre(),
                cmd.categoria(),
                cmd.precioUnitario(),
                cmd.stockInicial()
        );
        writeRepo.guardar(producto);
        return producto.getId();
    }
}