package com.example.inventariocqrs.command;

public record EliminarProductoCommand(
        String productoId
) {}