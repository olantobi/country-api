package com.liferon.countryapi.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
	@Bean
    public Docket countryAPI() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Country API")
                .apiInfo(apiInfo())                
                .select()
                //.apis(RequestHandlerSelectors.basePackage("com.liferon.countryapi.resource"))
                .paths(Predicates.and(Predicates.and(PathSelectors.any(),
                        Predicates.not(PathSelectors.regex("/error.*")),
                        Predicates.not(PathSelectors.regex("/oauth/auth.*")),
                        Predicates.not(PathSelectors.regex("/oauth/check.*")),
                        Predicates.not(PathSelectors.regex("/oauth/token_.*")),
                        Predicates.not(PathSelectors.regex("/oauth/confirm_.*")),
                        Predicates.not(PathSelectors.regex("/oauth/err.*"))
                )))
                //.paths(ant("/countries**"))
                //.paths(regex("/"))
                .build();
    }
	
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Country API")
                .description("Country API")
                .termsOfServiceUrl("http://www.liferon.com/terms-conditions")
                .version("1.0")
                .build();
    }
//
//    private Predicate<String> paths() {
//        return Predicates.and(
//                PathSelectors.regex("/countries.*"),
//                PathSelectors.regex("/login.*"),
//                PathSelectors.regex("/oauth/token.*"));
//                //Predicates.not(PathSelectors.regex("/oauth/token.*"));
//    }

}
