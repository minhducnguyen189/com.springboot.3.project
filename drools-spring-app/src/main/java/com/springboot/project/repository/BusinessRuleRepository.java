package com.springboot.project.repository;

import com.springboot.project.entity.BusinessRule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BusinessRuleRepository extends MongoRepository<BusinessRule, UUID> {

}
