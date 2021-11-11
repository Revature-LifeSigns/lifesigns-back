package com.revature.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	// Spring security by default will block all unauthorized http requests. You can configure the settings in this class.
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.authorizeRequests().antMatchers("/delete/**").authenticated().and().httpBasic().and().csrf().disable();
    	// The above currently will only need authorization for delete mappings (401 error if not) 
    	// and every other mapping will be allowed
    }
}
