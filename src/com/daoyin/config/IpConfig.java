package com.daoyin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class IpConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/admin/**").permitAll()
			.antMatchers("/file/**").access("hasIpAddress('127.0.0.1/24')");
			http.csrf().disable();
			http.headers().frameOptions().disable();
			http.headers().httpStrictTransportSecurity().disable();
	
	}
	
}
