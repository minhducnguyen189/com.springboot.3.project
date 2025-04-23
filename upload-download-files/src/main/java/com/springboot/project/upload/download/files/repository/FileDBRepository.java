package com.springboot.project.upload.download.files.repository;

import com.springboot.project.upload.download.files.entity.FileDB;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDBRepository extends JpaRepository<FileDB, UUID> {}
