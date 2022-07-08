package com.example.adminservice.service;

import com.example.adminservice.dto.ApiResponse;
import com.example.adminservice.dto.CategoryDto;
import com.example.adminservice.entity.Category;
import com.example.adminservice.mapper.CategoryMapper;
import com.example.adminservice.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public record CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
    public ApiResponse<Category> add(CategoryDto dto) {
        Category category = categoryMapper.toEntity(dto);
        if (dto.getParentId() == null) {
            category.setParent(null);
        } else {
            category.setParent(categoryRepository.findByIdAndActiveTrue(dto.getParentId()).orElse(category.getParent()));
        }
        Category save = categoryRepository.save(category);
        return ApiResponse.<Category>builder()
                .success(true)
                .object(save)
                .message("ADDED")
                .build();
    }

    public ApiResponse<Category> edit(Long id, CategoryDto dto) {
        Optional<Category> optionalCategory = categoryRepository.findByIdAndActiveTrue(id);
        if (optionalCategory.isEmpty()) {
            return ApiResponse.<Category>builder().message("NOT FOUND").build();
        }
        Category category = optionalCategory.get();
        categoryMapper.update(dto, category);
        if(dto.getParentId()==null){
            category.setParent(null);
        }else {
            category.setParent(categoryRepository.findByIdAndActiveTrue(dto.getParentId()).orElse(category.getParent()));
        }
        Category save = categoryRepository.save(category);
        return ApiResponse.<Category>builder()
                .success(true)
                .object(save)
                .message("Edited!")
                .build();
    }
}
