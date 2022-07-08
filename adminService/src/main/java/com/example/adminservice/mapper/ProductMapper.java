package com.example.adminservice.mapper;

import com.example.adminservice.dto.ProductDTO;
import com.example.adminservice.entity.Attachment;
import com.example.adminservice.entity.Product;
import lombok.SneakyThrows;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDTO toDTO(Product product);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "picture", expression = "java(attachment(dto.getPhoto()))")
    Product toEntity(ProductDTO productDTO);

    @SneakyThrows
    @Named("attachment")
    default Attachment attachment(MultipartFile file) {
        return Attachment.builder()
                .name(file.getOriginalFilename())
                .bytes(file.getBytes())
                .size(file.getSize())
                .type(file.getContentType())
                .build();
    }

    List<ProductDTO> toDTOList(List<Product> productList);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "photo", expression = "java(attachmentEdit(dto.getPhoto(), book.getPhoto()))")
    void update(ProductDTO dto, @MappingTarget Product product);

    @SneakyThrows
    @Named("attachmentEdit")
    default Attachment attachmentEdit(MultipartFile file, Attachment attachment) {
        attachment.setBytes(file.getBytes());
        attachment.setName(file.getOriginalFilename());
        attachment.setSize(file.getSize());
        attachment.setType(file.getContentType());
        return attachment;
    }
}
