package com.example.adminservice.controller;

import com.example.adminservice.dto.ApiResponse;
import com.example.adminservice.dto.FilialDto;
import com.example.adminservice.entity.Filial;
import com.example.adminservice.repository.FilialRepository;
import com.example.adminservice.service.FilialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/filial")
public class FilialController {
    final FilialRepository filialRepository;
    final FilialService filialService;

    @PostMapping
    public ResponseEntity add(@RequestBody FilialDto filialDto) {
        ApiResponse<Filial> add = filialService.add(filialDto);
        return ResponseEntity.ok(add);
    }

    @PutMapping("/{id}")
    public ResponseEntity edit(@PathVariable Long id, @RequestBody FilialDto filialDto) {
        ApiResponse<Filial> edit = filialService.edit(id, filialDto);
        return ResponseEntity.ok(edit);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        ApiResponse<Filial> delete = filialService.delete(id);
        return ResponseEntity.ok(delete);
    }

    @GetMapping
    public ResponseEntity getAll() {
        List<Filial> byIdAAndActiveTrue = filialRepository.findAllByActiveTrue();
        return ResponseEntity.ok(byIdAAndActiveTrue);
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable Long id) {
        Optional<Filial> byId = filialRepository.findById(id);
        if (byId.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(byId.get());
    }
}
