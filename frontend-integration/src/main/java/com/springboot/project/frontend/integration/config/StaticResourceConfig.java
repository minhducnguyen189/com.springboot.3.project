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
import org.springframework.web.servlet.view.UrlBasedViewResolver;

@Configuration
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class StaticResourceConfig implements WebMvcConfigurer {

    private static final String DELIMITER = "/";

    private final PathResourceFirstAppResolverConfig pathResourceFirstAppResolverConfig;
    private final PathResourceSecondAppResolverConfig pathResourceSecondAppResolverConfig;
    private final ApplicationConfigurationProperty appConfig;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(
                        appConfig.getFrontEnd().getFirstAngularApp().getResourceHandlerPath())
                .addResourceLocations(
                        appConfig.getFrontEnd().getFirstAngularApp().getResourceLocations())
                .resourceChain(true)
                .addResolver(this.pathResourceFirstAppResolverConfig);
        registry.addResourceHandler(
                        appConfig.getFrontEnd().getSecondAngularApp().getResourceHandlerPath())
                .addResourceLocations(
                        appConfig.getFrontEnd().getSecondAngularApp().getResourceLocations())
                .resourceChain(true)
                .addResolver(this.pathResourceSecondAppResolverConfig);

        //        use directly resources from multiple-angular-apps
        //
        //        registry.addResourceHandler("/app1/**")
        //
        // .addResourceLocations("file:./multiple-angular-apps/dist/first-angular-app/")
        //                .resourceChain(true)
        //                .addResolver(this.pathResourceFirstAppResolverConfig);;
        //        registry.addResourceHandler("/app2/**")
        //
        // .addResourceLocations("file:./multiple-angular-apps/dist/second-angular-app/")
        //                .resourceChain(true)
        //                .addResolver(this.pathResourceSecondAppResolverConfig);;

    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        String app1ViewController =
                appConfig.getFrontEnd().getFirstAngularApp().getViewController();
        String app1ViewName = appConfig.getFrontEnd().getSecondAngularApp().getViewName();
        String app2ViewController =
                appConfig.getFrontEnd().getSecondAngularApp().getViewController();
        String app2ViewName = appConfig.getFrontEnd().getSecondAngularApp().getViewName();

        registry.addViewController(app1ViewController)
                .setViewName(this.getRedirectViewName(app1ViewController));
        registry.addViewController(app2ViewController)
                .setViewName(this.getRedirectViewName(app2ViewController));
        registry.addViewController(app1ViewController + DELIMITER)
                .setViewName(this.getForwardViewName(app1ViewName));
        registry.addViewController(app2ViewController + DELIMITER)
                .setViewName(this.getForwardViewName(app2ViewName));
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.addPathPrefix(
                appConfig.getBackEnd().getApiBasePath(),
                HandlerTypePredicate.forAnnotation(RestController.class));
    }

    private String getRedirectViewName(String viewController) {
        return UrlBasedViewResolver.REDIRECT_URL_PREFIX
                + appConfig.getBackEnd().getServerBasePath()
                + viewController
                + DELIMITER;
    }

    private String getForwardViewName(String viewName) {
        return UrlBasedViewResolver.FORWARD_URL_PREFIX + viewName;
    }
}
