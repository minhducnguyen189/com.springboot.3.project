package com.springboot.project.controller;

import com.springboot.project.generated.api.ProductApi;
import com.springboot.project.generated.model.ProductDetail;
import com.springboot.project.generated.model.ProductFilterResult;
import com.springboot.project.generated.model.ProductRequest;
import com.springboot.project.service.IProductService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ProductController implements ProductApi {

    private final IProductService productService;

    @Autowired
    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @Override
    public ResponseEntity<ProductDetail> addProduct(
            List<MultipartFile> files, ProductRequest json) {
        ProductDetail productDetail = this.productService.createProduct(files, json);
        return new ResponseEntity<>(productDetail, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ProductFilterResult> getProducts(
            Optional<Integer> pageNumber, Optional<Integer> pageSize) {
        ProductFilterResult productFilterResult =
                this.productService.getProducts(pageNumber.orElse(0), pageSize.orElse(18));
        return new ResponseEntity<>(productFilterResult, HttpStatus.OK);
    }
}
