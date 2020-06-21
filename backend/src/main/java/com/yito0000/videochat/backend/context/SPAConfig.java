package com.yito0000.videochat.backend.context;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.resource.VersionResourceResolver;

import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class SPAConfig implements WebMvcConfigurer {

    private final ResourceProperties resourceProperties;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/**")
                .addResourceLocations(resourceProperties.getStaticLocations())
                .resourceChain(resourceProperties.getChain().isCache())
                .addResolver(new SpaPageResourceResolver());
        new VersionResourceResolver().addContentVersionStrategy("/**");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:index.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    public static class SpaPageResourceResolver extends PathResourceResolver {

        @Override
        protected Resource getResource(String resourcePath, Resource location) throws IOException {
            // apiのパスは無視する
            if (resourcePath.startsWith("api/")) {
                return null;
            }
            Resource resource = super.getResource(resourcePath, location);

            // リソースが存在しなければindex.htmlを返す
            return resource != null ? resource : super.getResource("index.html", location);
        }
    }
}
