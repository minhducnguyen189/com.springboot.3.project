package com.springboot.project.frontend.integration.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "application.config")
public class ApplicationConfigurationProperty {

    private String apiBasePath;
    private AngularAppConfig firstAngularApp;
    private AngularAppConfig secondAngularApp;

    @Getter
    @Setter
    public static class AngularAppConfig {
        private String resourceHandlerPath;
        private String[] resourceLocations;
        private String indexLocation;
        private String viewController;
        private String viewName;
    }

}
