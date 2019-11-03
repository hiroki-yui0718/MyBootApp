package com.tuyano.springboot.controller;

import java.time.*;
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
		Suica state = service.find(line);
		ZonedDateTime t1 = ZonedDateTime.now();
		ZonedDateTime t2 = state.getDate();
		Duration t = Duration.between(t1,t2);
		long sumTime = t.toHours();
		if(state.getState().equals("出勤")) {
			suica.setIdm(line);
			suica.setState("退勤");
			suica.setTime(sumTime);
			mav.addObject("msg",line + "さんの退勤を受け付けました");
		}else {
			suica.setIdm(line);
			suica.setState("出勤");
			suica.setTime(0);
			mav.addObject("msg",line + "さんの出勤を受け付けました");
		}
		suica.setDate(t1);
		repository.saveAndFlush(suica);
		
		List<Suica> list = service.getAll();
		mav.addObject("datalist",list);
		return mav;
	}

}
