package com.tuyano.springboot.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tuyano.springboot.model.Account;
import com.tuyano.springboot.repositories.AccountRepository;

@Controller
public class AuthenticationController {

	@Autowired
	AccountRepository repository;
	
	@PostConstruct
	public void init(){
		Account d1 = new Account();
		d1.setUsername("yui");
		d1.setPassword("password");
		repository.saveAndFlush(d1);
	}
	
	@RequestMapping(value="/login")
	public String viewLoginForm() {
		return "loginForm";
	}
	@RequestMapping(value="/signup",method=RequestMethod.GET)
	public ModelAndView signup(@ModelAttribute Account account,ModelAndView mav) {
		return mav;
		
	}
}
