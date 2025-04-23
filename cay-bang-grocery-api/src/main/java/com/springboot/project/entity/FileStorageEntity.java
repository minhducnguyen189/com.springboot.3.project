package com.springboot.project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "file_storage")
public class FileStorageEntity extends BaseEntity {

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_extension")
    private String fileExtension;

    @Column(name = "media_type")
    private String mediaType;

    @Column(name = "file_data")
    private byte[] fileData;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity product;
}
