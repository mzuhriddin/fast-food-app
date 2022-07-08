package com.example.adminservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public class CategoryDto implements Serializable {
    private String name;
    private Long parentId;
}