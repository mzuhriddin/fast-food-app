package com.example.adminservice.service;

import com.example.adminservice.dto.ApiResponse;
import com.example.adminservice.dto.ProductDTO;
import com.example.adminservice.entity.Product;
import com.example.adminservice.mapper.ProductMapper;
import com.example.adminservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public record ProductService(ProductRepository productRepository,
                             ProductMapper productMapper) {

    public ApiResponse add(ProductDTO productDTO) {
        if (productDTO.getPhoto().isEmpty()) {
            return ApiResponse.builder().message("Photo must not be empty").build();
        }
        if (!Objects.requireNonNull(productDTO.getPhoto().getOriginalFilename()).matches("^(.+)\\.(png|jpeg|ico|jpg)$")) {
            return ApiResponse.builder()
                    .success(false)
                    .message("File type must be png, jpeg, ico, jpg")
                    .build();
        }
        Product product = productMapper.toEntity(productDTO);
        productRepository.save(product);
        return ApiResponse.builder().success(true).message("ADDED").build();
    }

    public ApiResponse getOne(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            return ApiResponse.builder().message("NOT FOUND").build();
        }
        ProductDTO productDTO = productMapper.toDTO(optionalProduct.get());
        return ApiResponse.<ProductDTO>builder().success(true).object(productDTO).message("ONE_PRODUCT").build();
    }

    public ApiResponse getAll() {
        List<Product> productList = productRepository.findAll();
        List<ProductDTO> productDTOList = productMapper.toDTOList(productList);
        return ApiResponse.<List<ProductDTO>>builder().object(productDTOList).success(true).message("ALL_PRODUCT").build();
    }

    public ApiResponse edit(Long id, ProductDTO productDTO) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            return ApiResponse.builder().message("NOT FOUND").build();
        }
        if (productDTO.getPhoto().isEmpty()) {
            return ApiResponse.builder().message("Photo must not be empty").build();
        }
        if (!Objects.requireNonNull(productDTO.getPhoto().getOriginalFilename()).matches("^(.+)\\.(png|jpeg|ico|jpg)$")) {
            return ApiResponse.builder()
                    .success(false)
                    .message("File type must be png, jpeg, ico, jpg")
                    .build();
        }
        Product product = optionalProduct.get();
        productMapper.update(productDTO, product);
        productRepository.save(product);
        return ApiResponse.builder().message("EDITED").build();
    }


}