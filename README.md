# OpenAPI 3.0

### *Set up spring-fox openapi*

```xml
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-boot-starter</artifactId>
    <version>3.0.0</version>
</dependency>
```

### *Configuration*
```java
@EnableOpenApi
@Configuration
public class SwaggerConfig {

  @Bean
  public Docket apiDocket() {
    return new Docket(DocumentationType.OAS_30)
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
            .paths(PathSelectors.ant("/api/**"))
            .build()
            .groupName("GroupName");
  }

  private ApiInfo apiInfo() {
    Contact contact = new Contact("Contact Name", "Contact URL", "Contact E-MAIL");

    return new ApiInfoBuilder()
            .title("Open API Title")
            .description("Description Open API")
            .version("v1.0")
            .contact(contact)
            .build();
  }

}
```

## Reference

* [Swagger](https://swagger.io "The Best APIs are Built with Swagger Tools | Swagger")
* [Springfox Reference Documentation](http://springfox.github.io/springfox/docs/snapshot/ "Springfox Reference Documentation")
