package com.example.adminservice.repository;

import com.example.adminservice.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByActiveTrue();

    Optional<Category> findByIdAndActiveTrue(Long id);
}
