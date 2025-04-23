package com.springboot.project.upload.download.files.service;

import com.springboot.project.upload.download.files.config.ApplicationProperty;
import com.springboot.project.upload.download.files.entity.FileDB;
import com.springboot.project.upload.download.files.model.FileContentDto;
import com.springboot.project.upload.download.files.model.FileDto;
import com.springboot.project.upload.download.files.repository.FileDBRepository;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class FileStorageService {

    private final FileDBRepository fileDBRepository;
    private final ApplicationProperty applicationProperty;

    public FileDto saveFile(MultipartFile uploadFile) {
        if (Objects.isNull(uploadFile.getOriginalFilename())) {
            throw new IllegalArgumentException("FileName can not be null");
        }
        String fileName = StringUtils.cleanPath(uploadFile.getOriginalFilename());
        FileDB fileDB = new FileDB();
        fileDB.setFileName(fileName);
        fileDB.setFileExtension(FilenameUtils.getExtension(fileName));
        fileDB.setFileType(uploadFile.getContentType());
        try {
            fileDB.setFileData(uploadFile.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can not map File Content");
        }
        return this.mapToFileDto(this.fileDBRepository.save(fileDB));
    }

    public FileContentDto getFileData(UUID id) {
        Optional<FileDB> fileDB = this.fileDBRepository.findById(id);
        if (fileDB.isPresent()) {
            return this.mapToFileContentDto(fileDB.get());
        }
        throw new RuntimeException("File Not Found");
    }

    public List<FileDto> getFilesInformation() {
        List<FileDB> fileDBs = this.fileDBRepository.findAll();
        return fileDBs.stream().map(this::mapToFileDto).collect(Collectors.toList());
    }

    private FileDto mapToFileDto(FileDB fileDB) {
        FileDto fileDto = new FileDto();
        fileDto.setId(fileDB.getId());
        fileDto.setFileName(fileDB.getFileName());
        fileDto.setFileType(fileDB.getFileType());
        fileDto.setCreatedDate(fileDB.getCreatedDate());
        fileDto.setUpdatedDate(fileDB.getUpdatedDate());
        fileDto.setFileExtension(fileDB.getFileExtension());
        fileDto.setLocation(this.buildLocationUrl(fileDB.getId()));
        return fileDto;
    }

    private FileContentDto mapToFileContentDto(FileDB fileDB) {
        FileContentDto fileContentDto = new FileContentDto();
        fileContentDto.setFileName(fileDB.getFileName());
        fileContentDto.setContent(fileDB.getFileData());
        return fileContentDto;
    }

    private String buildLocationUrl(UUID fileId) {
        return this.applicationProperty.getServerBaseUrl()
                + this.applicationProperty.getGetFileApi()
                + fileId;
    }
}
