package com.springboot.project.service;

import com.springboot.project.config.ApplicationConfig;
import com.springboot.project.entity.FileStorageEntity;
import com.springboot.project.entity.ProductEntity;
import com.springboot.project.generated.model.FileDetail;
import com.springboot.project.generated.model.ProductDetail;
import com.springboot.project.generated.model.ProductFilterResult;
import com.springboot.project.generated.model.ProductRequest;
import com.springboot.project.mapper.ProductMapper;
import com.springboot.project.repository.ProductRepository;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ApplicationConfig applicationConfig;

    @Autowired
    public ProductService(ProductRepository productRepository,
                          ApplicationConfig applicationConfig) {
        this.productRepository = productRepository;
        this.applicationConfig = applicationConfig;
    }

    public ProductDetail createProduct(List<MultipartFile> files, ProductRequest productRequest) {
        ProductEntity product = ProductMapper.MAPPER.toProductEntity(productRequest);
        product.setFiles(this.buildFileStorageEntity(files, product));
        ProductDetail productDetail = ProductMapper.MAPPER.toProductDetail(this.productRepository.save(product));
        for (FileDetail fileDetail: productDetail.getImages()) {
            fileDetail.setFileUrl(applicationConfig.getServerBaseUrl() +
                    MessageFormat.format(applicationConfig.getGetFileApi(), productDetail.getId(), fileDetail.getId()));
        }
        return productDetail;
    }

    public ProductFilterResult getProducts(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        ProductFilterResult productFilterResult = new ProductFilterResult();
        Page<ProductEntity> page = this.productRepository.findAll(pageable);
        Long count = this.productRepository.count();

        List<ProductDetail> productDetails = ProductMapper.MAPPER.toProductDetails(page.getContent());
        for (ProductDetail productDetail : productDetails) {
            productDetail.getImages().forEach(image -> {
                image.setFileUrl(applicationConfig.getServerBaseUrl() +
                        MessageFormat.format(applicationConfig.getGetFileApi(), productDetail.getId(), image.getId()));
            });
        }
        productFilterResult.setProducts(productDetails);
        productFilterResult.setFoundNumber(count);
        productFilterResult.setTotal(count);
        return productFilterResult;

    }

    public ProductEntity getProductByProductNumber(Long productNumber) {
        ProductEntity product = this.productRepository.findByProductNumber(productNumber);
        if (Objects.nonNull(product)) {
            return product;
        }
        throw new RuntimeException("Product Not Found!");
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
