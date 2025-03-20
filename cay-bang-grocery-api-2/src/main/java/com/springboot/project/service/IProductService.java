package com.springboot.project.service;

import com.springboot.project.generated.model.ProductDetail;
import com.springboot.project.generated.model.ProductFilterResult;
import com.springboot.project.generated.model.ProductRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IProductService {

  ProductDetail createProduct(List<MultipartFile> files, ProductRequest productRequest);

  ProductFilterResult getProducts(Integer pageNumber, Integer pageSize);
}
