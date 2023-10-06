package com.springboot.project.frontend.integration.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StaticResourceConfig implements WebMvcConfigurer {

    @Autowired
    private PathResourceFirstAppResolverConfig pathResourceFirstAppResolverConfig;
    @Autowired
    private PathResourceSecondAppResolverConfig pathResourceSecondAppResolverConfig;


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/app1/**")
                .addResourceLocations("classpath:/static/first-angular-app/")
                .resourceChain(true)
                .addResolver(this.pathResourceFirstAppResolverConfig);
        registry.addResourceHandler("/app2/**")
                .addResourceLocations("classpath:/static/second-angular-app/")
                .resourceChain(true)
                .addResolver(this.pathResourceSecondAppResolverConfig);


//        use directly resources from multiple-angular-apps
//
//        registry.addResourceHandler("/app1/**")
//                .addResourceLocations("file:./multiple-angular-apps/dist/first-angular-app/")
//                .resourceChain(true)
//                .addResolver(this.pathResourceFirstAppResolverConfig);;
//        registry.addResourceHandler("/app2/**")
//                .addResourceLocations("file:./multiple-angular-apps/dist/second-angular-app/")
//                .resourceChain(true)
//                .addResolver(this.pathResourceSecondAppResolverConfig);;

    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/app1/").setViewName("forward:/app1/index.html");
        registry.addViewController("/app2/").setViewName("forward:/app2/index.html");

    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.addPathPrefix("/v1/**", HandlerTypePredicate.forAnnotation(RestController.class));
    }


}
