package com.example.inventariocqrs.query.model;

import java.math.BigDecimal;

public record ProductoView(
        String id,
        String nombre,
        String categoria,
        BigDecimal precioUnitario,
        int stockDisponible,
        String estadoStock
) {
    public static String calcularEstado(int stock) {
        if (stock == 0) return "AGOTADO";
        if (stock < 5) return "BAJO";
        return "DISPONIBLE";
    }
}