package com.example.inventariocqrs.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class Producto {

    private final ProductoId id;
    private String nombre;
    private String categoria;
    private BigDecimal precioUnitario;
    private int stockDisponible;

    public Producto(ProductoId id, String nombre, String categoria,
                    BigDecimal precio, int stockInicial) {
        this.id = Objects.requireNonNull(id, "ID es obligatorio");

        if (nombre == null || nombre.isBlank())
            throw new IllegalArgumentException("Nombre es obligatorio");
        if (precio == null || precio.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Precio debe ser positivo");

        this.nombre = nombre;
        this.categoria = categoria;
        this.precioUnitario = precio;
        this.stockDisponible = Math.max(0, stockInicial);
    }

    public void incrementarStock(int unidades) {
        if (unidades <= 0)
            throw new IllegalArgumentException("Unidades deben ser positivas");
        this.stockDisponible += unidades;
    }

    public void reducirStock(int unidades) {
        if (unidades > this.stockDisponible)
            throw new StockInsuficienteException(id, stockDisponible, unidades);
        this.stockDisponible -= unidades;
    }

    public ProductoId getId()             { return id; }
    public String getNombre()             { return nombre; }
    public String getCategoria()          { return categoria; }
    public BigDecimal getPrecioUnitario() { return precioUnitario; }
    public int getStockDisponible()       { return stockDisponible; }
}