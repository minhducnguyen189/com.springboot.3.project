package com.springboot.project.mapper;

import com.springboot.project.config.ApplicationConfig;
import com.springboot.project.entity.FileStorageEntity;
import com.springboot.project.entity.ProductEntity;
import com.springboot.project.generated.model.FileDetail;
import com.springboot.project.generated.model.ProductDetail;
import com.springboot.project.generated.model.ProductRequest;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);

    ProductEntity toProductEntity(ProductRequest productRequest);

    @Mapping(target = "images", source = "files")
    ProductDetail toProductDetail(ProductEntity productEntity);

    default List<FileDetail> toImages(List<FileStorageEntity> fileStorageEntities) {
            List<FileDetail> fileDetails = new ArrayList<>();
            for(FileStorageEntity fileStorageEntity: fileStorageEntities) {
                FileDetail fileDetail = new FileDetail();
                fileDetail.setFileExtension(fileStorageEntity.getFileExtension());
                fileDetail.setId(fileStorageEntity.getId());
                fileDetail.setFileName(fileStorageEntity.getFileName());
                fileDetail.setMediaType(fileStorageEntity.getMediaType());
                fileDetail.setCreatedDate(fileStorageEntity.getCreatedAt());
                fileDetail.setUpdatedDate(fileStorageEntity.getUpdatedAt());
                fileDetails.add(fileDetail);
            }
            return fileDetails;
    }

    @AfterMapping
    default void after(FileStorageEntity fileStorageEntity, @MappingTarget FileDetail target, @Context ApplicationConfig config) {
        target.setFileUrl(config.getServerBaseUrl() +
                MessageFormat.format(config.getGetFileApi(), fileStorageEntity.getProduct().getId(), fileStorageEntity.getId()));
    }

}
