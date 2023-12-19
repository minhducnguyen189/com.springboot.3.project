package com.springboot.project.controller;

import com.springboot.project.config.ApplicationConfig;
import com.springboot.project.generated.api.ProductApi;
import com.springboot.project.generated.model.FileDetail;
import com.springboot.project.generated.model.ProductDetail;
import com.springboot.project.generated.model.ProductFilterResult;
import com.springboot.project.generated.model.ProductRequest;
import com.springboot.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

@RestController
public class ProductController implements ProductApi {

    private final ProductService productService;
    private final ApplicationConfig config;

    @Autowired
    public ProductController(ProductService productService,
                             ApplicationConfig config) {
        this.productService = productService;
        this.config = config;
    }

    @Override
    public ResponseEntity<ProductDetail> addProduct(List<MultipartFile> files, ProductRequest json) {
        ProductDetail productDetail = this.productService.createProduct(files, json);
        for (FileDetail fileDetail: productDetail.getImages()) {
            fileDetail.setFileUrl(config.getServerBaseUrl() +
                    MessageFormat.format(config.getGetFileApi(), productDetail.getId(), fileDetail.getId()));
        }
        return new ResponseEntity<>(productDetail, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ProductFilterResult> getProducts(Optional<Integer> pageNumber, Optional<Integer> pageSize) {
        ProductFilterResult productFilterResult = this.productService
                .getProducts(pageNumber.orElse(0), pageSize.orElse(18));
        for(ProductDetail productDetail: productFilterResult.getProducts()) {
            productDetail.getImages().forEach(image -> {
                image.setFileUrl(config.getServerBaseUrl() +
                        MessageFormat.format(config.getGetFileApi(), productDetail.getId(), image.getId()));
            });
        }
        return new ResponseEntity<>(productFilterResult, HttpStatus.OK);
    }

}
