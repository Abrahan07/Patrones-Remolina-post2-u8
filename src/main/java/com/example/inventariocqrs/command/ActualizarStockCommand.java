package com.example.inventariocqrs.command;

public record ActualizarStockCommand(
        String productoId,
        int delta,
        String motivo
) {}