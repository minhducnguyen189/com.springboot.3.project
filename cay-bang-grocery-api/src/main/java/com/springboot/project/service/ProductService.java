package com.springboot.project.service;

import com.springboot.project.entity.FileStorageEntity;
import com.springboot.project.entity.ProductEntity;
import com.springboot.project.generated.model.ProductDetail;
import com.springboot.project.generated.model.ProductRequest;
import com.springboot.project.mapper.ProductMapper;
import com.springboot.project.repository.ProductRepository;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDetail createProduct(List<MultipartFile> files, ProductRequest productRequest) {
        ProductEntity product = ProductMapper.MAPPER.toProductEntity(productRequest);
        product.setFiles(this.buildFileStorageEntity(files, product));
        return ProductMapper.MAPPER.toProductDetail(this.productRepository.save(product));
    }

    private List<FileStorageEntity> buildFileStorageEntity(List<MultipartFile> files, ProductEntity product) {
        List<FileStorageEntity> fileStorageEntities = new ArrayList<>();
        for (MultipartFile file: files) {
            if (Objects.isNull(file.getOriginalFilename())) {
                throw new IllegalArgumentException("FileName can not be null");
            }
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            FileStorageEntity fileDB = new FileStorageEntity();
            fileDB.setFileName(fileName);
            fileDB.setFileExtension(FilenameUtils.getExtension(fileName));
            fileDB.setMediaType(file.getContentType());
            fileDB.setProduct(product);
            try {
                fileDB.setFileData(file.getBytes());
            } catch (IOException e) {
                throw new RuntimeException("Can not map File Content");
            }
            fileStorageEntities.add(fileDB);
        }
        return fileStorageEntities;
    }

}