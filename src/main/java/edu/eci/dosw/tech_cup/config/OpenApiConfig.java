package edu.eci.dosw.tech_cup.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI techCupOpenApi() {
        return new OpenAPI().info(new Info()
                .title("TechCup Futbol API")
                .description("Documentacion de endpoints del primer ciclo del laboratorio")
                .version("1.0")
                .contact(new Contact()
                        .name("Equipo TechCup")
                        .email("techcup@eci.edu.co")));
    }
}
