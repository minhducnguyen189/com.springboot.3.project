package com.springboot.project.repository;

import com.springboot.project.model.Customer;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerRepository extends CouchbaseRepository<Customer, UUID> {

}
