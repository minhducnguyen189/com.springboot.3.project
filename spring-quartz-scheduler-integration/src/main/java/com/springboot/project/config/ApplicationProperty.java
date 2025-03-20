package com.springboot.project.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "application")
public class ApplicationProperty {

  private Quartz quartz;

  @Getter
  @Setter
  public static class Quartz {
    private String timeZone;
  }
}
