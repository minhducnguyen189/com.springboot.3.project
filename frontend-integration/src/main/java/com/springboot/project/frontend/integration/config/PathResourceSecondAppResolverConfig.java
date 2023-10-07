package com.springboot.project.frontend.integration.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.IOException;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PathResourceSecondAppResolverConfig extends PathResourceResolver {

    private final ApplicationConfigurationProperty appConfig;

    @Override
    public Resource getResource(String resourcePath, Resource location) throws IOException {
        Resource requestedResource = location.createRelative(resourcePath);
        return requestedResource.exists() && requestedResource.isReadable() ? requestedResource
                : new ClassPathResource(this.appConfig.getSecondAngularApp().getIndexLocation());
    }

}
