package com.cap.pocvng.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean

    public OpenAPI customOpenAPI() {

        return new OpenAPI()
                .addServersItem(new Server().url("https://exampleurl.com"))
                .components(new Components())
                .info(new Info()
                        .title("UM API vacatures tussen UM en gemeenten").version("0.8.2")
                        .description("Contract tbv interactie van gemeenten met UM voor vacatures")
                        .contact(new Contact().name("VNG").url("https://www.vngrealisatie.nl/").email("realisatie@vng.nl")));


    }
}
