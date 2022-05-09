package api.birdout.config.swagger;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import api.birdout.dto.auth.AuthDto;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@Slf4j
public class SwaggerConfig {

  @Value("${jwt.header-key}")
  private String AUTHORIZATION_HEADER;
  
  @Bean
  public Docket api() {
    return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .ignoredParameterTypes(ignoreSchemas())
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()))
                .useDefaultResponseMessages(false) // swagger에서 기본으로 제공하는 응답코드 제거
                .select()
                .apis(RequestHandlerSelectors.basePackage("api.birdout.controller")) // swagger가 자동문서화 시키는 범위
                .paths(PathSelectors.any())
                .build();
  }

  // scheams ignore list
  private Class[] ignoreSchemas() {
    Class[] ignoreClass = {
      AuthDto.class
    };
    return ignoreClass;
  }

  private ApiKey apiKey() {
    return new ApiKey(AUTHORIZATION_HEADER, "JWT", "header");
  }

  private SecurityContext securityContext() {
    return SecurityContext.builder()
                          .securityReferences(defaultAuth())
                          .build();
  }

  private List<SecurityReference> defaultAuth() {
    AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
    authorizationScopes[0] = authorizationScope;
    return Arrays.asList(new SecurityReference(AUTHORIZATION_HEADER, authorizationScopes));
  }

  private ApiInfo apiInfo() {
      return new ApiInfoBuilder()
                .title("Message Mart2 Open API(BirdOut)")
                .description("API문서입니다.")
                .version("1.0.0")
                .license("")
                .licenseUrl("")
                .build();
  }

}
