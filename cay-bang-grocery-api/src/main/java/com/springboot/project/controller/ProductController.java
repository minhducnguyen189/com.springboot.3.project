package com.springboot.project.controller;

import com.springboot.project.generated.api.ProductApi;
import com.springboot.project.generated.model.ProductDetail;
import com.springboot.project.generated.model.ProductRequest;
import com.springboot.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController implements ProductApi {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public ResponseEntity<ProductDetail> addProduct(List<MultipartFile> files, ProductRequest json) {
        return new ResponseEntity<>(this.productService.createProduct(files, json), HttpStatus.CREATED);
    }

}
