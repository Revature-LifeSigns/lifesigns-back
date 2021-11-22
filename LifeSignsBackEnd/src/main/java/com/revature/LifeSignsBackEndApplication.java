package com.revature;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@Slf4j
public class LifeSignsBackEndApplication {

	public static void main(String[] args) {
		log.trace("Java application is running.");
		SpringApplication.run(LifeSignsBackEndApplication.class, args);
	}

}
