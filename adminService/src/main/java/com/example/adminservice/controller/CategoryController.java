package com.example.adminservice.controller;

import com.example.adminservice.dto.ApiResponse;
import com.example.adminservice.dto.CategoryDto;
import com.example.adminservice.entity.Category;
import com.example.adminservice.repository.CategoryRepository;
import com.example.adminservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    final CategoryService categoryService;
    final CategoryRepository categoryRepository;

    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.ok().body(categoryRepository.findAllByActiveTrue());
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable Long id) {
        Optional<Category> optionalCategory = categoryRepository.findByIdAndActiveTrue(id);
        if (optionalCategory.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(optionalCategory.orElseThrow());
    }

    @PostMapping
    public ResponseEntity add(@RequestBody CategoryDto dto) {
        ApiResponse<Category> apiResponse = categoryService.add(dto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity edit(@RequestBody CategoryDto dto, @PathVariable Long id) {
        ApiResponse<Category> apiResponse = categoryService.edit(id, dto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        ApiResponse<Object> apiResponse = categoryService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }
}