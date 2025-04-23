package com.springboot.project.repository;

import com.springboot.project.model.JsonSchemaValidator;
import java.util.List;
import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JsonSchemaRepository extends MongoRepository<JsonSchemaValidator, UUID> {

    List<JsonSchemaValidator> findJsonSchemaValidatorByName(String name);

    List<JsonSchemaValidator> findByOrderByVersionDesc(String name);
}
