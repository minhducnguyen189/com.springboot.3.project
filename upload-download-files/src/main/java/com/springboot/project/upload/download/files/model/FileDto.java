package com.springboot.project.upload.download.files.model;

import java.util.Date;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileDto {

    private UUID id;
    private String fileName;
    private String fileType;
    private String fileExtension;
    private Date createdDate;
    private Date updatedDate;
    private String location;
}
