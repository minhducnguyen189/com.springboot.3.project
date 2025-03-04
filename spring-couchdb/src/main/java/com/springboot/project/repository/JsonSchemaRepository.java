package com.springboot.project.repository;

import com.springboot.project.model.JsonSchemaValidator;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JsonSchemaRepository extends CouchbaseRepository<JsonSchemaValidator, UUID> {

    List<JsonSchemaValidator> findJsonSchemaValidatorByName(String name);

    List<JsonSchemaValidator> findByOrderByVersionDesc(String name);

}
