package com.example.numberGenerateService.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Number-generate service API",
                description = "Number-generate service", version = "1.0.0",
                contact = @Contact(
                        name = "Renat Mingazov",
                        email = "mingazofff@gmail.com"
                )
        )
)
public class SwaggerConfig {
}
