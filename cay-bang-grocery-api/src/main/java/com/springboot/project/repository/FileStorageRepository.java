package com.springboot.project.repository;

import com.springboot.project.entity.FileStorageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FileStorageRepository extends JpaRepository<FileStorageEntity, UUID> {

  Optional<FileStorageEntity> findByProductIdAndId(UUID productId, UUID fileId);
}
