package com.tuyano.springboot.controller;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tuyano.springboot.model.Suica;
import com.tuyano.springboot.repositories.SuicaRepository;
import com.tuyano.springboot.service.SuicaService;
import com.tuyano.springboot.socket.*;

@Controller
public class ServerController {

	@Autowired
	SuicaRepository repository;
	
	@Autowired
	SuicaService service;
	
	@RequestMapping("/server")
	public ModelAndView server(ModelAndView mav) {
		mav.setViewName("server");
		ServerSide s1 = new ServerSide();
		String line = s1.runSample();
		
		Suica suica = new Suica();
		suica.setId(line);
		ZonedDateTime d = ZonedDateTime.now();
		suica.setDate(d);
		suica.setState(true);
		List<Suica> list = service.getAll();
		mav.addObject("datalist",list);
		mav.addObject("state","出勤");
		mav.addObject("time","2019");
		return mav;
	}
//	@RequestMapping("/show_id")
//	public ModelAndView send(ModelAndView mav) {
//		List<Suica> list = service.findId();
//		mav.addObject("datalist",list);
//		return mav;
//	}

}
