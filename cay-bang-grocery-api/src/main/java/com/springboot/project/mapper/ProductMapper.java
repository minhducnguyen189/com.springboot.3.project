package com.springboot.project.mapper;

import com.springboot.project.entity.FileStorageEntity;
import com.springboot.project.entity.ProductEntity;
import com.springboot.project.generated.model.FileDetail;
import com.springboot.project.generated.model.ProductDetail;
import com.springboot.project.generated.model.ProductRequest;
import java.util.ArrayList;
import java.util.List;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);

    ProductEntity toProductEntity(ProductRequest productRequest);

    List<ProductDetail> toProductDetails(List<ProductEntity> productEntities);

    @Mapping(target = "images", source = "files")
    ProductDetail toProductDetail(ProductEntity productEntity);

    default List<FileDetail> toImages(List<FileStorageEntity> fileStorageEntities) {
        List<FileDetail> fileDetails = new ArrayList<>();
        for (FileStorageEntity fileStorageEntity : fileStorageEntities) {
            FileDetail fileDetail = new FileDetail();
            fileDetail.setFileExtension(fileStorageEntity.getFileExtension());
            fileDetail.setId(fileStorageEntity.getId());
            fileDetail.setFileName(fileStorageEntity.getFileName());
            fileDetail.setMediaType(fileStorageEntity.getMediaType());
            fileDetail.setCreatedAt(fileStorageEntity.getCreatedAt());
            fileDetail.setUpdatedAt(fileStorageEntity.getUpdatedAt());
            fileDetails.add(fileDetail);
        }
        return fileDetails;
    }
}
