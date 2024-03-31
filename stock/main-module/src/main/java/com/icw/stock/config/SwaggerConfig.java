package com.icw.stock.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Swagger springdoc-ui 구성 파일
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {

        Info info = new Info()
            .version("v1.0.0")
            .title("Stock Manager")
            .description("Stock Manager API 입니다");

        return new OpenAPI()
            .info(info);
    }

}