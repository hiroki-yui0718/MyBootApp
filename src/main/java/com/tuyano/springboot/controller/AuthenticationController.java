package com.tuyano.springboot.controller;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	
	@Autowired
	PasswordEncoder passwordEncoder;
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
	public ModelAndView signup(ModelAndView mav) {
		mav.setViewName("signup");
		return mav;
		
	}
	@RequestMapping(value="/signup",method=RequestMethod.POST)
	public ModelAndView send(Account account,HttpServletRequest request,ModelAndView mav) {
		mav.setViewName("signup");
		String username = request.getParameter("username");
		String str1 = request.getParameter("password");
		String str2 = request.getParameter("password-con");
		if(str1.equals(str2)) {
			account.setUsername(username);
			account.setPassword(passwordEncoder.encode(str1));
			repository.saveAndFlush(account);
			mav = new ModelAndView("redirect:/");
		}else {
			mav = new ModelAndView("redirect:/signup");
		}
		return mav;
		
	}
}
