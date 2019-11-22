package com.tuyano.springboot.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tuyano.springboot.model.Account;
import com.tuyano.springboot.repositories.AccountRepository;
import com.tuyano.springboot.service.ManagementService;

@Controller
public class EmployeeController {

	@Autowired
	ManagementService service;

	@Autowired
	AccountRepository repository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@RequestMapping(value = "/employee", method = RequestMethod.GET)
	public ModelAndView get(ModelAndView mav) {
		List<Account> list = service.findDataAll();
		mav.addObject("datalist",list);
		mav.setViewName("employee");
		return mav;
	}
	@RequestMapping(value = "/employee", params="delete",method = RequestMethod.POST)
	public ModelAndView delete(HttpServletRequest request,ModelAndView mav) {
		String username = request.getParameter("username");
		repository.deleteById(username);
		return mav = new ModelAndView("redirect:/employee");
	}
	@RequestMapping(value = "/employee", params="update",method = RequestMethod.POST)
	public ModelAndView post(@ModelAttribute("formModel") Account account,HttpServletRequest request,ModelAndView mav) {
		repository.save(account);


		return mav = new ModelAndView("redirect:/employee");
	}
}
