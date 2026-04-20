package com.example.inventariocqrs.command;

import java.math.BigDecimal;

public record AgregarProductoCommand(
        String nombre,
        String categoria,
        BigDecimal precioUnitario,
        int stockInicial
) {}