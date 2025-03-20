package com.springboot.project.config;

import com.springboot.project.service.SimpleJob;
import org.quartz.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Configuration
public class QuartzConfig {

  @Bean
  public CommandLineRunner run(Scheduler scheduler) {
    return (String[] args) -> {
      JobDetail job =
          JobBuilder.newJob(SimpleJob.class)
              .usingJobData("param", "value") // add a parameter
              .build();

      Date afterFiveSeconds =
          Date.from(LocalDateTime.now().plusSeconds(30).atZone(ZoneId.systemDefault()).toInstant());
      Trigger trigger = TriggerBuilder.newTrigger().startAt(afterFiveSeconds).build();

      scheduler.scheduleJob(job, trigger);
    };
  }
}
