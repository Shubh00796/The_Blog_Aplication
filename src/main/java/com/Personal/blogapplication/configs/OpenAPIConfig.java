package com.Personal.blogapplication.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("THE ALL IN ON API")
                        .description("THE ALL IN ON API")
                        .version("1.0")
                        .termsOfService("http://example.com/terms")
                        .contact(new Contact().name("Shubham").email("itzzshubh00796@gmail.com"))
                        .license(new License().name("Apache 2.0").url("http://www.apache.org/licenses/LICENSE-2.0.html")))
                .servers(List.of(new Server().url("http://localhost:8080"), new Server().url("https://your-production-url.com")));
    }
}