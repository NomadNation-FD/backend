package com.github.francodurand.nomadnation.shared.infrastructure.documentation.openapi.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfiguration {
        // Properties
        @Value("${spring.application.name}")
        String applicationName;

        @Bean
        public OpenAPI customOpenAPI() {
                // General configuration
                var openApi = new OpenAPI();

                openApi.info(new Info().title(this.applicationName))
                                .externalDocs(new ExternalDocumentation()
                                                .description("GitHub repository")
                                                .url("https://github.com/NomadNation-FD"));

                // Add security scheme

                final String securitySchemeName = "bearerAuth";

                openApi.addSecurityItem(new SecurityRequirement()
                                .addList(securitySchemeName))
                                .components(new Components()
                                                .addSecuritySchemes(securitySchemeName,
                                                                new SecurityScheme()
                                                                                .name(securitySchemeName)
                                                                                .type(SecurityScheme.Type.HTTP)
                                                                                .scheme("bearer")
                                                                                .bearerFormat("JWT")));

                // Return OpenAPI configuration object with all the settings

                return openApi;
        }
}