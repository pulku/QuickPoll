package com.pulku;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.models.dto.builder.ApiInfoBuilder;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.inject.Inject;

/**
 * Created by pınar on 28.03.2016.
 */
@Configuration
@EnableSwagger
public class SwaggerConfig {

    @Inject
    private SpringSwaggerConfig springSwaggerConfig;

    private ApiInfo getApiInfo() {

        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("QuickPoll REST API")
                .description("QuickPoll API for creating and managing polls")
                .termsOfServiceUrl("http://example.com/terms-of-service")
                .contact("info@quickpoll.com")
                .license("MIT License")
                .licenseUrl("http://opensource.org/licenses/MIT")
                .build();

        return apiInfo;
    }

    @Bean
    public SwaggerSpringMvcPlugin v1APIConfiguration() {
        SwaggerSpringMvcPlugin swaggerSpringMvcPlugin = new SwaggerSpringMvcPlugin(this.springSwaggerConfig);

        swaggerSpringMvcPlugin.apiInfo(getApiInfo())
                              .apiVersion("1.0")
                              .includePatterns("/v1/*.*")
                              .swaggerGroup("v1")
                              .useDefaultResponseMessages(false);
        return swaggerSpringMvcPlugin;
    }

    @Bean
    public SwaggerSpringMvcPlugin v2APIConfiguration() {
        SwaggerSpringMvcPlugin swaggerSpringMvcPlugin = new SwaggerSpringMvcPlugin(this.springSwaggerConfig);

        swaggerSpringMvcPlugin.apiInfo(getApiInfo())
                .apiVersion("2.0")
                .includePatterns("/v2/*.*")
                .swaggerGroup("v2")
                .useDefaultResponseMessages(false);
        return swaggerSpringMvcPlugin;
    }

    @Bean
    public SwaggerSpringMvcPlugin v3APIConfiguration() {
        SwaggerSpringMvcPlugin swaggerSpringMvcPlugin = new SwaggerSpringMvcPlugin(this.springSwaggerConfig);

        swaggerSpringMvcPlugin.apiInfo(getApiInfo())
                .apiVersion("3.0")
                .includePatterns("/v3/*.*")
                .swaggerGroup("v3")
                .useDefaultResponseMessages(false);
        return swaggerSpringMvcPlugin;
    }
}
