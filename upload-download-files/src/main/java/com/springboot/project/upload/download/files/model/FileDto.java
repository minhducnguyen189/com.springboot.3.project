package com.springboot.project.upload.download.files.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class FileDto {

    private UUID id;
    private String fileName;
    private String fileType;
    private Date createdDate;
    private Date updatedDate;

}
