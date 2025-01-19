package com.aitp.categoryproduct.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aitp.categoryproduct.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}