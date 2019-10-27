package com.tuyano.springboot.config;

import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	public void configure(WebSecurity web) {
		//x除外する方
		web.ignoring().antMatchers("/webjars/bootstrap/**");
	}
	@Override
	public void configure(HttpSecurity http) throws Exception{
		//x認証設定を書く方
		http.formLogin().defaultSuccessUrl("/index.html").loginPage("/login")
		.loginProcessingUrl("/authenticate").usernameParameter("uid").passwordParameter("pwd").permitAll();
		http.authorizeRequests().anyRequest().authenticated();
	}

}
