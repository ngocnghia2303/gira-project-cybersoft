package cybersoft.javabackend.java16giranghia.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket getDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.securityContexts(List.of(getSecurityContext()))
				.securitySchemes(List.of(getSecurityScheme()))
				.select()
				.apis(RequestHandlerSelectors.basePackage("cybersoft.javabackend.java16giranghia")).build()
				.apiInfo(new ApiInfoBuilder().title("Gira Application").version("1.0.0")
						.description("This project is used for education purpose only.")
						.contact(new Contact("Nghia Do", "https://24h.com.vn", "ngocty756@gmail.com")).build());
	}

	private ApiKey getSecurityScheme() {
		return new ApiKey("JWT", "Authorization", "header");
	} 

	private SecurityContext getSecurityContext() {
		return SecurityContext.builder()
				.securityReferences(List.of(getSecurityReference()))
				.build();
	}

	private SecurityReference getSecurityReference() {
		// Call to constructor
		AuthorizationScope globalScope = new AuthorizationScope("global", "use for all request");
		AuthorizationScope scopes[] = new AuthorizationScope[1];
		scopes[0] = globalScope;
		
		return new SecurityReference("JWT", scopes);
		
	}
	
}
