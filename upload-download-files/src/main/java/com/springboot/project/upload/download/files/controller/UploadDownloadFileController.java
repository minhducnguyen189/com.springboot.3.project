package com.springboot.project.upload.download.files.controller;

import com.springboot.project.upload.download.files.model.FileContentDto;
import com.springboot.project.upload.download.files.model.FileDto;
import com.springboot.project.upload.download.files.service.FileStorageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@CrossOrigin("*")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RestController
public class UploadDownloadFileController {

    private final FileStorageService fileStorageService;

    @RequestMapping(method = RequestMethod.POST, path = "/v1/files", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FileDto> uploadFile(@RequestParam("file") MultipartFile uploadFile) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.fileStorageService.saveFile(uploadFile));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/v1/files/{id}", produces = {MediaType.APPLICATION_OCTET_STREAM_VALUE})
    public ResponseEntity<byte[]> downloadFile(@PathVariable("id") UUID id) {
        FileContentDto fileContentDto = this.fileStorageService.getFileData(id);
        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + fileContentDto.getFileName() + "\"")
                .body(fileContentDto.getContent());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/v1/files", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FileDto>> getFilesInformation() {
        return ResponseEntity.status(HttpStatus.OK).body(this.fileStorageService.getFilesInformation());
    }

}
