package com.aitp.categoryproduct.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aitp.categoryproduct.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}