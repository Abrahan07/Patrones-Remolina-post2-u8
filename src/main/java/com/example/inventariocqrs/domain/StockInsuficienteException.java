package com.example.inventariocqrs.domain;

public class StockInsuficienteException extends RuntimeException {

    public StockInsuficienteException(ProductoId id, int stockActual, int unidadesSolicitadas) {
        super(String.format(
                "Stock insuficiente para producto [%s]. Disponible: %d, Solicitado: %d",
                id.getValor(), stockActual, unidadesSolicitadas
        ));
    }
}