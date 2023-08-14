package com.example.AuthorManagement.repositories;

import com.example.AuthorManagement.model.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    public List<Category> findByNameContainingIgnoreCase(String name);
}
