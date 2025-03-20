package com.springboot.project.mapper;

import com.springboot.project.entity.EmailSchedulerEntity;
import com.springboot.project.generated.model.EmailSchedulerDataRequest;
import com.springboot.project.generated.model.EmailSchedulerDataResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmailSchedulerMapper {

  EmailSchedulerMapper MAPPER = Mappers.getMapper(EmailSchedulerMapper.class);

  EmailSchedulerEntity toEmailSchedulerEntity(EmailSchedulerDataRequest emailSchedulerDataRequest);

  EmailSchedulerDataResponse toEmailSchedulerDataResponse(EmailSchedulerEntity entity);

  void updateEmailSchedulerEntity(
      @MappingTarget EmailSchedulerEntity entity,
      EmailSchedulerDataRequest emailSchedulerDataRequest);
}
