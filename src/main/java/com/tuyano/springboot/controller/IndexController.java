package com.tuyano.springboot.controller;

import java.util.List;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tuyano.springboot.model.Data;
import com.tuyano.springboot.model.Management;
import com.tuyano.springboot.service.AccountService;
import com.tuyano.springboot.service.DataService;
import com.tuyano.springboot.service.ManagementService;

@Controller
public class IndexController {
	
	@Autowired
	DataService service;
	@Autowired
	ManagementService service2;
	@Autowired
	AccountService service3;
	
	@RequestMapping("/")
	public ModelAndView index(ModelAndView mav,HttpServletRequest request,Authentication authentication) {
		mav.setViewName("index");
		mav.addObject("msg","message 1<hr>message 2<br>message 3");
		try {
			List<Data> list = service.getAll();
			mav.addObject("datalist",list);

		}catch(NullPointerException e) {
			e.printStackTrace();
		}
		try {
			User userDetail = (User)authentication.getPrincipal();
			String name = userDetail.getUsername();
			long id = service3.findId(name);
			mav.addObject("id","calendar/"+id+"/0");
		}catch(NoResultException e) {
			mav.addObject("id","\"#\"");
		}


		try{
			Management management = service2.getLog();
			mav.addObject("data",management);
		}catch(NoResultException e) {
			e.printStackTrace();
			mav.addObject("data",null);
		}
		return mav;
	}
}
