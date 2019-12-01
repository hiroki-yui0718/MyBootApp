package com.tuyano.springboot.controller;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TransactionRequiredException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tuyano.springboot.model.Account;
import com.tuyano.springboot.model.Data;
import com.tuyano.springboot.repositories.AccountRepository;
import com.tuyano.springboot.repositories.CalRepository;
import com.tuyano.springboot.repositories.ManagementRepository;
import com.tuyano.springboot.repositories.DataRepository;
import com.tuyano.springboot.service.AccountService;
import com.tuyano.springboot.service.ManagementService;
import com.tuyano.springboot.service.ReservationUserDetailsService;

@Controller
public class ManagementController {

	@Autowired
	ManagementService service;
	@Autowired
	ReservationUserDetailsService service2;
	@Autowired
	AccountService service3;
	@Autowired
	AccountRepository repository;

	@RequestMapping(value = "/management", method = RequestMethod.GET)
	public ModelAndView show(ModelAndView mav) {
		List<Account> list = service3.findDataAll();
		mav.addObject("datalist",list);
		mav.setViewName("management");
		return mav;
	}
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView get(ModelAndView mav) {
		List<Account> list = service3.findDataAll();
		mav.addObject("datalist",list);
		mav.setViewName("admin");
		return mav;
	}
	@RequestMapping(value = "/admin/{id}", params="delete",method = RequestMethod.POST)
	public ModelAndView delete(@PathVariable long id,HttpServletRequest request,ModelAndView mav) {
		repository.deleteById(id);
		return mav = new ModelAndView("redirect:/admin");
	}
	@RequestMapping(value = "/admin/{id}", params="update",method = RequestMethod.POST)
	public ModelAndView post(@PathVariable long id,HttpServletRequest request,ModelAndView mav) {
		String role = request.getParameter("role");		
		service2.RoleUpdate(id,role);
		return mav = new ModelAndView("redirect:/admin");
	}
}
