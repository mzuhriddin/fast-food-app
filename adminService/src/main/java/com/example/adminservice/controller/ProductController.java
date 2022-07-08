package com.example.adminservice.controller;


import com.example.adminservice.dto.ApiResponse;
import com.example.adminservice.dto.ProductDTO;
import com.example.adminservice.entity.Product;
import com.example.adminservice.repository.ProductRepository;
import com.example.adminservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductRepository productRepository;
    private final ProductService productService;

    @PostMapping
    public ResponseEntity save(@ModelAttribute ProductDTO productDTO) {
        ApiResponse add = productService.add(productDTO);
        return ResponseEntity.ok(add);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        ApiResponse response = productService.getOne(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity getALL() {
        ApiResponse all = productService.getAll();
        return ResponseEntity.ok(all);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        Optional<Product> user = productRepository.findById(id);
        if (user.isEmpty()) {
            return ResponseEntity.ok("NOT FOUND");
        }
        productRepository.delete(user.get());
        return ResponseEntity.ok("DELETED");
    }

    @PutMapping("/{id}")
    public ResponseEntity edit(@PathVariable Long id, @RequestBody ProductDTO dto) {
        ApiResponse response = productService.edit(id, dto);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

}
