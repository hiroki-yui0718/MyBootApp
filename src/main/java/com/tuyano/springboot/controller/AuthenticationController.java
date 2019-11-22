package com.tuyano.springboot.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tuyano.springboot.model.Account;
import com.tuyano.springboot.repositories.AccountRepository;
import com.tuyano.springboot.service.ManagementService;
import com.tuyano.springboot.socket.ServerSide;

@Controller
public class AuthenticationController {

	@Autowired
	ManagementService service;

	@Autowired
	AccountRepository repository;

	@Autowired
	PasswordEncoder passwordEncoder;
	@PostConstruct
	public void init(){
	}

	@RequestMapping(value="/login")
	public ModelAndView viewLoginForm(ModelAndView mav,HttpSession session) {
		mav.addObject("session",null);
		mav.setViewName("loginForm");
		return mav;
	}
	@RequestMapping(value="/signup",method=RequestMethod.GET)
	public ModelAndView signup(ModelAndView mav) {
		ServerSide s1 = new ServerSide();
		String line = s1.runSample();
		mav.addObject("idm",line);
		mav.setViewName("signup");
		return mav;

	}
	@RequestMapping(value="/signup",method=RequestMethod.POST)
	public ModelAndView send(Account account,HttpServletRequest request,ModelAndView mav) {
		mav.setViewName("signup");
		String idm = request.getParameter("idm");
		String username = request.getParameter("username");
		String str1 = request.getParameter("password");
		String str2 = request.getParameter("password-con");
		String str3 = request.getParameter("role");
		if(str1.equals(str2)) {
			account.setIdm(idm);
			account.setUsername(username);
			account.setPassword(passwordEncoder.encode(str1));
			if(str3.equals("admin")) {
				account.setRole("ADMIN");
			}else {
				account.setRole("EMPLOYEE");
			}
			repository.saveAndFlush(account);
			mav = new ModelAndView("redirect:/");


		}else {
			mav = new ModelAndView("redirect:/signup");
		}
		return mav;

	}

}
