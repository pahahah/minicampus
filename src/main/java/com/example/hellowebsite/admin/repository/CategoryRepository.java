package com.example.hellowebsite.admin.repository;

import com.example.hellowebsite.admin.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
