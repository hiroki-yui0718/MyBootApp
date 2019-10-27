package com.tuyano.springboot.controller;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tuyano.springboot.model.Account;

@Controller
public class AuthenticationController {

	@PostConstruct
	public void init() {
		Account d1 = new Account();
		d1.setUserName("yui");
		d1.setPassword("password");
	}

	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String viewLoginForm() {
		return "loginForm";
	}
}
