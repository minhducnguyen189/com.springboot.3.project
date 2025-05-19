package com.springboot.project.repository;

import com.springboot.project.entity.BusinessRule;
import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessRuleRepository extends MongoRepository<BusinessRule, UUID> {}
