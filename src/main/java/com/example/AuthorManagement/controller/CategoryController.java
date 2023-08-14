package com.example.AuthorManagement.controller;

import com.example.AuthorManagement.model.dto.requestDto.CategoryRequestDto;
import com.example.AuthorManagement.model.dto.responseDto.CategoryResponseDto;
import com.example.AuthorManagement.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public ResponseEntity<List<CategoryResponseDto>> getCategories() {
        List<CategoryResponseDto> categoryResponseDtos = categoryService.getCategories();
        return new ResponseEntity<>(categoryResponseDtos, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<CategoryResponseDto> saveCategory(
            @RequestBody final CategoryRequestDto categoryRequestDto) {
        CategoryResponseDto categoryResponseDto = categoryService.addCategory(categoryRequestDto);
        return new ResponseEntity<>(categoryResponseDto, HttpStatus.OK);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryResponseDto> editCategory(
            @PathVariable final Long categoryId,
            @RequestBody final CategoryRequestDto categoryRequestDto) {
        CategoryResponseDto categoryResponseDto = categoryService.editCategory(categoryId, categoryRequestDto);
        return new ResponseEntity<>(categoryResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<CategoryResponseDto> deleteCategory(
            @PathVariable final Long categoryId) {
        CategoryResponseDto categoryResponseDto = categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(categoryResponseDto, HttpStatus.OK);
    }

    @GetMapping("/find/{categoryId}")
    public ResponseEntity<CategoryResponseDto> findByCategoryId(
            @PathVariable final Long categoryId) {
        CategoryResponseDto categoryResponseDto = categoryService.getCategoryById(categoryId);
        return new ResponseEntity<>(categoryResponseDto, HttpStatus.OK);
    }

    @GetMapping("/findName")
    public ResponseEntity<List<CategoryResponseDto>> findCategorisByNameContaining(
            @RequestParam final String name
    ) {
        List<CategoryResponseDto> categoryResponseDtos = categoryService.findByNameContainingIgnoreCase(name);
        return new ResponseEntity<>(categoryResponseDtos, HttpStatus.OK);
    }
}
