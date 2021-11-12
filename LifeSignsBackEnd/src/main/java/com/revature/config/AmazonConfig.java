package com.revature.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AmazonConfig {
   
	ClassLoader cl = getClass().getClassLoader();
	InputStream is;
	Properties p= new Properties();
    
	public AmazonConfig() {
		
	}
    
    @Bean
    public AmazonS3 s3() {
    	
        return AmazonS3ClientBuilder
                .standard()
                .withRegion("us-east-1")
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .build();

    }
}
