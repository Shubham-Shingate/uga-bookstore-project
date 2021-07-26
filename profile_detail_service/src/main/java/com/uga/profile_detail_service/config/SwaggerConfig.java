package com.uga.profile_detail_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import static springfox.documentation.builders.PathSelectors.regex;
import java.util.ArrayList;
import java.util.List;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

	/*
	 * The Below commented code is to create swagger api documentation by seeing the
	 * controller rest endpoints(Code to documentation)
	 */

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.uga.profile_detail_service")).paths(regex("/.*")).build()
				.apiInfo(metaInfo());
	}

	private ApiInfo metaInfo() {
		List<VendorExtension> vendorExtensions = new ArrayList<>();
		ApiInfo apiInfo = new ApiInfo("UGA Online Bookstore Project", "Swagger API documentation dashboard for UGA Online Bookstore Project",
				"1.0", "Terms of Service", new Contact("Shubham", "", "shubhamshingte2234@gmail.com"),
				"Apache License Version 2.0", "https://www.apache.org/licesen.html", vendorExtensions);

		return apiInfo;
	}

}