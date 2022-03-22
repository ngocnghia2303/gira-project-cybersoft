package cybersoft.javabackend.java16giranghia.config;

import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	public Docket getDocket() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("cybersoft.javabackend.java16giranghia")).build()
				.apiInfo(new ApiInfoBuilder().title("Gira Application").version("1.0.0")
						.description("This project is used for education purpose only.")
						.contact(new Contact("Nghia Do", "https://24h.com.vn", "ngocty756@gmail.com")).build());
	}
}
