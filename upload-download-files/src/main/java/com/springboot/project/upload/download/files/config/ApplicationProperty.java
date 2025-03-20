package com.springboot.project.upload.download.files.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "application")
public class ApplicationProperty {

  private String serverBaseUrl;
  private String getFileApi;
}
