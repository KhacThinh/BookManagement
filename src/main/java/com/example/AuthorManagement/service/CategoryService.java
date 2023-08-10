package com.example.AuthorManagement.service;

import com.example.AuthorManagement.model.dto.requestDto.CategoryRequestDto;
import com.example.AuthorManagement.model.dto.responseDto.CategoryResponseDto;
import com.example.AuthorManagement.model.entities.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    public List<CategoryResponseDto> getCategories();

    public Category getCategory(Long categoryId);

    public CategoryResponseDto addCategory(CategoryRequestDto categoryRequestDto);

    public CategoryResponseDto getCategoryById(Long categoryId);

    public CategoryResponseDto editCategory(Long categoryId, CategoryRequestDto categoryRequestDto);

    public CategoryResponseDto deleteCategory(Long categoryId);

}
