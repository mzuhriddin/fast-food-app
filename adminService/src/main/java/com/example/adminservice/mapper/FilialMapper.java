package com.example.adminservice.mapper;

import com.example.adminservice.dto.FilialDto;
import com.example.adminservice.entity.Filial;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FilialMapper {
    //    @Named("time")
//    default LocalTime toTime(String time){
//        return LocalTime.parse(time);
//    }
//    @Mapping(source = "start",target = "start",qualifiedByName = "time")
//    @Mapping(source = "finish",target = "finish",qualifiedByName = "time")
    Filial toEntity (FilialDto filialDto);

}
