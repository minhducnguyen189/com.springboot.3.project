package com.springboot.project.upload.download.files.entity;

import jakarta.persistence.*;
import java.util.Date;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "files")
public class FileDB {

    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;

    private String fileName;
    private String fileType;
    private String fileExtension;
    private byte[] fileData;
    private Date createdDate;
    private Date updatedDate;

    @PrePersist
    private void onCreate() {
        Date now = new Date();
        this.createdDate = now;
        this.updatedDate = now;
    }

    @PreUpdate
    private void onUpdate() {
        this.updatedDate = new Date();
    }
}
