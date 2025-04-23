package com.springboot.project.repository;

import com.springboot.project.entity.EmailSchedulerEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<EmailSchedulerEntity, UUID> {}
