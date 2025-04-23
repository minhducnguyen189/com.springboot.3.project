package com.springboot.project.repository;

import com.springboot.project.repository.entity.ProductEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {

    ProductEntity findByProductNumber(Long productNumber);
}
