package com.springboot.project.frontend.integration.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class StaticResourceConfig implements WebMvcConfigurer {

    private final PathResourceFirstAppResolverConfig pathResourceFirstAppResolverConfig;
    private final PathResourceSecondAppResolverConfig pathResourceSecondAppResolverConfig;
    private final ApplicationConfigurationProperty appConfig;


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(appConfig.getFirstAngularApp().getResourceHandlerPath())
                .addResourceLocations(appConfig.getFirstAngularApp().getResourceLocations())
                .resourceChain(true)
                .addResolver(this.pathResourceFirstAppResolverConfig);
        registry.addResourceHandler(appConfig.getSecondAngularApp().getResourceHandlerPath())
                .addResourceLocations(appConfig.getSecondAngularApp().getResourceLocations())
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
        registry.addViewController(appConfig.getFirstAngularApp().getViewController())
                .setViewName(appConfig.getFirstAngularApp().getViewName());
        registry.addViewController(appConfig.getSecondAngularApp().getViewController())
                .setViewName(appConfig.getSecondAngularApp().getViewName());
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.addPathPrefix(appConfig.getApiBasePath(), HandlerTypePredicate.forAnnotation(RestController.class));
    }


}
