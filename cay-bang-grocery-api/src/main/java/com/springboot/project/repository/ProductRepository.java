package com.springboot.project.repository;

import com.springboot.project.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {

  ProductEntity findByProductNumber(Long productNumber);
}
