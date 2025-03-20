package com.springboot.project.service;

import com.springboot.project.entity.EmailSchedulerEntity;
import com.springboot.project.generated.model.EmailSchedulerDataRequest;
import com.springboot.project.generated.model.EmailSchedulerDataResponse;
import com.springboot.project.mapper.EmailSchedulerMapper;
import com.springboot.project.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class EmailDataService {

  private final EmailRepository emailRepository;

  @Autowired
  public EmailDataService(EmailRepository emailRepository) {
    this.emailRepository = emailRepository;
  }

  public EmailSchedulerDataResponse createEmailScheduleData(EmailSchedulerDataRequest request) {
    EmailSchedulerEntity entity = EmailSchedulerMapper.MAPPER.toEmailSchedulerEntity(request);
    return EmailSchedulerMapper.MAPPER.toEmailSchedulerDataResponse(
        this.emailRepository.save(entity));
  }

  public EmailSchedulerDataResponse updateEmailScheduleData(
      UUID id, EmailSchedulerDataRequest request) {
    EmailSchedulerEntity entity = this.getEmailSchedulerEntity(id);
    EmailSchedulerMapper.MAPPER.updateEmailSchedulerEntity(entity, request);
    return EmailSchedulerMapper.MAPPER.toEmailSchedulerDataResponse(
        this.emailRepository.save(entity));
  }

  public EmailSchedulerDataResponse getEmailEmailScheduleData(UUID id) {
    return EmailSchedulerMapper.MAPPER.toEmailSchedulerDataResponse(
        this.getEmailSchedulerEntity(id));
  }

  public EmailSchedulerEntity getEmailSchedulerEntity(UUID id) {
    Optional<EmailSchedulerEntity> opt = this.emailRepository.findById(id);
    if (opt.isEmpty()) {
      throw new RuntimeException("resource not found!");
    }
    return opt.get();
  }
}
