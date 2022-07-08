package com.example.adminservice.repository;

import com.example.adminservice.entity.Filial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilialRepository extends JpaRepository<Filial, Long> {
    List<Filial> findAllByActiveTrue();

}
