package com.pankaj.kyc.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI kycOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("KYC Document Verification API")
                        .description("Microservice for ID verification, OCR extraction and Face Matching.")
                        .version("1.0.0"));
    }
}
