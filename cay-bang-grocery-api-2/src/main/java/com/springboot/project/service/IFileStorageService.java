package com.springboot.project.service;

import com.springboot.project.generated.model.FileResourceDetail;

import java.util.UUID;

public interface IFileStorageService {

    FileResourceDetail downloadFile(UUID productId, UUID fileId);

}
