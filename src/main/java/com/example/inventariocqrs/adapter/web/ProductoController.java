package com.example.inventariocqrs.adapter.web;

import com.example.inventariocqrs.command.AgregarProductoCommand;
import com.example.inventariocqrs.command.ActualizarStockCommand;
import com.example.inventariocqrs.command.EliminarProductoCommand;
import com.example.inventariocqrs.command.handler.AgregarProductoHandler;
import com.example.inventariocqrs.command.handler.ActualizarStockHandler;
import com.example.inventariocqrs.command.handler.EliminarProductoHandler;
import com.example.inventariocqrs.domain.ProductoId;
import com.example.inventariocqrs.query.BuscarProductoQuery;
import com.example.inventariocqrs.query.ListarProductosQuery;
import com.example.inventariocqrs.query.handler.BuscarProductoQueryHandler;
import com.example.inventariocqrs.query.handler.ListarProductosQueryHandler;
import com.example.inventariocqrs.query.model.ProductoView;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/inventario")
public class ProductoController {

    // ── Command handlers ─────────────────────────────────
    private final AgregarProductoHandler agregarHandler;
    private final ActualizarStockHandler stockHandler;
    private final EliminarProductoHandler eliminarHandler;

    // ── Query handlers ───────────────────────────────────
    private final BuscarProductoQueryHandler buscarHandler;
    private final ListarProductosQueryHandler listarHandler;

    public ProductoController(
            AgregarProductoHandler agregarHandler,
            ActualizarStockHandler stockHandler,
            EliminarProductoHandler eliminarHandler,
            BuscarProductoQueryHandler buscarHandler,
            ListarProductosQueryHandler listarHandler) {
        this.agregarHandler = agregarHandler;
        this.stockHandler = stockHandler;
        this.eliminarHandler = eliminarHandler;
        this.buscarHandler = buscarHandler;
        this.listarHandler = listarHandler;
    }

    // ── Rutas de COMANDO (modifican estado) ──────────────

    @PostMapping("/productos")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, String> agregar(@RequestBody AgregarProductoCommand cmd) {
        ProductoId id = agregarHandler.handle(cmd);
        return Map.of("productoId", id.toString());
    }

    @PatchMapping("/productos/{id}/stock")
    public Map<String, String> actualizarStock(@PathVariable String id,
                                               @RequestBody ActualizarStockCommand cmd) {
        String msg = stockHandler.handle(
                new ActualizarStockCommand(id, cmd.delta(), cmd.motivo())
        );
        return Map.of("mensaje", msg);
    }

    @DeleteMapping("/productos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable String id) {
        eliminarHandler.handle(new EliminarProductoCommand(id));
    }

    // ── Rutas de CONSULTA (solo lectura) ─────────────────

    @GetMapping("/productos")
    public List<ProductoView> listar(
            @RequestParam(defaultValue = "false") boolean soloDisponibles) {
        return listarHandler.handle(new ListarProductosQuery(soloDisponibles));
    }

    @GetMapping("/productos/{id}")
    public ProductoView buscar(@PathVariable String id) {
        return buscarHandler.handle(new BuscarProductoQuery(id));
    }
}