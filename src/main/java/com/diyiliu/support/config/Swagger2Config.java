package com.diyiliu.support.config;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: Swagger2Config
 * Author: DIYILIU
 * Update: 2018-05-10 15:19
 */

@EnableSwagger2
@Configuration
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {

        List<ApiKey> apiKeys = new ArrayList();
        apiKeys.add(new ApiKey("Authorization", "Authorization", "header"));

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(apiKeys);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("springboot 利用 swagger 构建 api 文档")
                .description("简单优雅的 RESTful  风格,个人主页 http://diyiliu.cc")
                .termsOfServiceUrl("http://hao.diyiliu.cc")
                .version("2.0")
                .build();
    }
}
