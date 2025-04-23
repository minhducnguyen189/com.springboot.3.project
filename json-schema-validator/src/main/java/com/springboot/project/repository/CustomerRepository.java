package com.springboot.project.repository;

import com.springboot.project.model.Customer;
import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, UUID> {}
