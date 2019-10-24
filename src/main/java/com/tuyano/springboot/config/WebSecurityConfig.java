package com.tuyano.springboot.config;

import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("resource/index.html");
	}
	@Override
	public void configure(HttpSecurity http) throws Exception{
		http.formLogin().loginPage("/login").permitAll();
		http.authorizeRequests().anyRequest().authenticated();
	}

}
