package com.springboot.project.frontend.integration.config;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.IOException;

@Component
public class PathResourceFirstAppResolverConfig extends PathResourceResolver {

    @Override
    public Resource getResource(String resourcePath, Resource location) throws IOException {
        Resource requestedResource = location.createRelative(resourcePath);
        return requestedResource.exists() && requestedResource.isReadable() ? requestedResource
                : new ClassPathResource("/static/first-angular-app/index.html");
    }
}
