package com.tuyano.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.AbstractRequestMatcherRegistry;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/resources/index.html").antMatchers("/webjars/bootstrap/**");
	}
	@Override
	public void configure(HttpSecurity http) throws Exception{
		//x認証設定を書く方
		http.formLogin().defaultSuccessUrl("/index.html").loginPage("/login")
		.loginProcessingUrl("/authenticate").usernameParameter("uid").passwordParameter("pwd").permitAll();
		http.authorizeRequests().anyRequest().authenticated();
	}
	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	void configureAuthenticationManger(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder());
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();

	}
}
