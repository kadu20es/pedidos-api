package br.com.alurafood.pedidos.config;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class SwaggerConfig {

    public static final String BEARER_KEY_SECURITY_SCHEME = "bearer-key";

    @Value("${spring.application.name}")
    private String applicationName;

    /*@Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList(Se))
                .components(new Components().addSecuritySchemes(BEARER_KEY_SECURITY_SCHEME,
                        new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")))
                .info(new Info()
                        .title(applicationName)
                        .version("1.0.0")
                        .description("Api de testes de pedidos"));
    }

    @Bean
    public GroupedOpenApi customApi(){
        return GroupedOpenApi.builder().group("api").pathsToMatch("/**").build();
    }*/


    /*@Bean
    public OpenAPI testOpenAPIDefinition() {
        final String securitySchemeName = "bearerAuth";

        return new OpenAPI().addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components().addSecuritySchemes(securitySchemeName,
                        new SecurityScheme().name(securitySchemeName).type(SecurityScheme.Type.HTTP).scheme("bearer")
                                .bearerFormat("JWT")))
                .info(new Info().title("Aplicação de teste")
                        .contact(new Contact().name("Equipe TJF").email("jv.frame.tjf@totvs.com.br"))
                        .description("Aplicação desenvolvida para testes.")
                        .version("v0.0.1"))
                .externalDocs(new ExternalDocumentation().description("Documentação")
                        .url("https://tjf.totvs.com.br/docs/open-api"));
    }*/

    /*@Bean
    public GroupedOpenApi testApi() {
        return GroupedOpenApi.builder().group("api-v1").packagesToScan("br.com.alurafood.pedidos")
                .pathsToMatch("/**").build();
    }**/
    /*
    @Profile("!dev")
    @Bean
    public GroupedOpenApi groupedPublicOpenApi10() {
        return GroupedOpenApi
                .builder()
                .addOpenApiCustomiser(openApi -> openApi.getInfo().setVersion("v1"))
                .group("API-v1")
                .pathsToMatch("/api/**")
                .displayName("API v1")
                .build();
    }*/
}
