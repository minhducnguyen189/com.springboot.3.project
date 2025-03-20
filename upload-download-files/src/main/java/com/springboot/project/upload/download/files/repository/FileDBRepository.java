package com.springboot.project.upload.download.files.repository;

import com.springboot.project.upload.download.files.entity.FileDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FileDBRepository extends JpaRepository<FileDB, UUID> {}
