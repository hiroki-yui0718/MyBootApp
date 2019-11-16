package com.tuyano.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/webjars/bootstrap/**");
	}
	@Override
	public void configure(HttpSecurity http) throws Exception{
		//x認証設定を書く方
		http.formLogin().loginPage("/login").successForwardUrl("/").permitAll();
		http.authorizeRequests().antMatchers("/login").permitAll().antMatchers("/").permitAll().antMatchers("/signup").permitAll().anyRequest().authenticated();
		http.logout().logoutUrl("/logout").permitAll();
		http.logout().logoutSuccessUrl("/").permitAll();
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
