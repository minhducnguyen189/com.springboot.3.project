package com.springboot.project.controller;

import com.springboot.project.generated.api.FileApi;
import com.springboot.project.generated.model.FileResourceDetail;
import com.springboot.project.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class FileStorageController implements FileApi {

    private final FileStorageService fileStorageService;

    @Autowired
    public FileStorageController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @Override
    public ResponseEntity<Resource> getFileData(UUID productId, UUID fileId) {
        FileResourceDetail fileResourceDetail = this.fileStorageService.downloadFile(productId, fileId);
        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileResourceDetail.getFileName() + "\"")
                .body(fileResourceDetail.getFileData());
    }

}
