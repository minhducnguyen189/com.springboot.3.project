package com.springboot.project.service;

import com.springboot.project.entity.FileStorageEntity;
import com.springboot.project.generated.model.FileResourceDetail;
import com.springboot.project.mapper.FileStorageMapper;
import com.springboot.project.repository.FileStorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class FileStorageService {

    private final FileStorageRepository fileStorageRepository;

    @Autowired
    public FileStorageService(FileStorageRepository fileStorageRepository) {
        this.fileStorageRepository = fileStorageRepository;
    }


    public FileResourceDetail downloadFile(UUID productId, UUID fileId) {
        Optional<FileStorageEntity> fileStorageEntity = this.fileStorageRepository.findByProductIdAndId(productId, fileId);
        if (fileStorageEntity.isPresent()) {
            return FileStorageMapper.MAPPER.toFileResourceDetail(fileStorageEntity.get());
        }
        throw new RuntimeException("File Resource Not Found!");
    }

}
