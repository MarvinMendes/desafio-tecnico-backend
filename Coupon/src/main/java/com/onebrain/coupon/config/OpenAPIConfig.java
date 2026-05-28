package com.onebrain.coupon.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI myOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Gerenciamento de Cupons")
                        .version("V1.0.0")
                        .description("Esta API expõe endpoints para criar e deletar cupons.")
                        .contact(new Contact().name("Marvin").email("marvin.m.lopes@gmail.com")));
    }
}
