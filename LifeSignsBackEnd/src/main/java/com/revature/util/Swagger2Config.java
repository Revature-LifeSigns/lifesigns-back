package com.revature.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class Swagger2Config {
    @Bean
    public Docket api()
    {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors
                        .basePackage("com.revature"))
                .paths(PathSelectors.regex("/.*"))
                .build()
                .apiInfo(apiEndPointsInfo());
    }
    
    private ApiInfo apiEndPointsInfo()
    {
        return new ApiInfoBuilder().title("Life Signs")
                .description("Life Signs: back-end API")
//                .contact(new Contact("Iaroslav Mokroguz",
//                        "http://www.revature.com",
//                        "maustrauk@gmail.com"))
//                .license("MIT")
                //.licenseUrl("https://github.com/LambdaSchool/java-springfoundation/blob/master/LICENSE")
                //.version("1.0.0")
                .build();
    }

}
