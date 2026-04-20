package com.example.inventariocqrs.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoJpaRepository extends JpaRepository<ProductoEntity, String> {}