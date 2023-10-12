package com.springboot.project.upload.download.files.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileContentDto {

    private String fileName;
    private byte[] content;

}
