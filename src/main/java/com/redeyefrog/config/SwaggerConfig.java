package com.redeyefrog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@EnableOpenApi
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket helloDocket() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(helloApiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .groupName("HelloOpenAPI");
    }

    private ApiInfo helloApiInfo() {
        Contact contact = new Contact("RedEyeFrog", "https://github.com/redeyefrog", "redeyefrog@contact.com");

        return new ApiInfoBuilder()
                .title("Hello Red Eye Frog")
                .description("This is OpenAPI")
                .version("v1.0")
                .contact(contact)
                .build();
    }

    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.ant("/api/**"))
                .build()
                .groupName("API");
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("RedEyeFrog", "https://github.com/redeyefrog", "redeyefrog@contact.com");

        return new ApiInfoBuilder()
                .title("OpenAPI")
                .description("This is OpenAPI 3.0")
                .version("v1.0")
                .contact(contact)
                .build();
    }

}
