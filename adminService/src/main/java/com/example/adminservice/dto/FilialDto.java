package com.example.adminservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FilialDto {

    private String nameUz, nameRu, intended;
    private String address;
    private LocalTime start, finish;
    private Float latitude, longitude;
}
