package com.example.inventariocqrs.domain;

import java.util.Objects;
import java.util.UUID;

public class ProductoId {

    private final String valor;

    public ProductoId(String valor) {
        this.valor = Objects.requireNonNull(valor, "El ID no puede ser nulo");
    }

    public static ProductoId nuevo() {
        return new ProductoId(UUID.randomUUID().toString());
    }

    public String getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductoId)) return false;
        ProductoId that = (ProductoId) o;
        return Objects.equals(valor, that.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }
}