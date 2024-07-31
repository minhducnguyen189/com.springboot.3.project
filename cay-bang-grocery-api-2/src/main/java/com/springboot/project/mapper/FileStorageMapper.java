package com.springboot.project.mapper;

import com.springboot.project.repository.entity.FileStorageEntity;
import com.springboot.project.generated.model.FileResourceDetail;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;

@Mapper
public interface FileStorageMapper {

    FileStorageMapper MAPPER = Mappers.getMapper(FileStorageMapper.class);

    FileResourceDetail toFileResourceDetail(FileStorageEntity fileStorageEntity);

    default Resource toFileData(byte[] fileData) {
        return new ByteArrayResource(fileData);
    }

}
