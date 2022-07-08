package com.example.adminservice.service;

import com.example.adminservice.dto.ApiResponse;
import com.example.adminservice.dto.FilialDto;
import com.example.adminservice.entity.Filial;
import com.example.adminservice.mapper.FilialMapper;
import com.example.adminservice.repository.FilialRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public record FilialService(FilialRepository filialRepository,
                            FilialMapper filialMapper) {
    public ApiResponse<Filial> add(FilialDto filialDto) {
        Filial filial = filialMapper.toEntity(filialDto);
        Filial save = filialRepository.save(filial);
        return ApiResponse.<Filial>builder().message("added").success(true).object(save).build();
    }

    public ApiResponse<Filial> edit(Long id, FilialDto filialDto) {
        Filial filial = filialMapper.toEntity(filialDto);
        filial.setId(id);
        Filial save = filialRepository.save(filial);
        return ApiResponse.<Filial>builder().message("edited").success(true).object(save).build();
    }

    public ApiResponse<Filial> delete(Long id) {
        Optional<Filial> byId = filialRepository.findById(id);
        if (byId.isEmpty()) {
            return ApiResponse.<Filial>builder().message("NOT FOUND").build();
        }
        Filial filial = byId.get();
        filial.setActive(false);
        filialRepository.save(filial);
        return ApiResponse.<Filial>builder().message("deleted").success(true).build();
    }

}
