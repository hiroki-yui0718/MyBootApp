package com.tuyano.springboot.controller;

import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.exception.DataException;
import org.hsqldb.HsqlException;
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

	@PostConstruct
	public void init() {
		Suica suica = new Suica();

	}

	@RequestMapping("/server")
	public ModelAndView server(ModelAndView mav) {

		mav.setViewName("server");
		ServerSide s1 = new ServerSide();
		String line = s1.runSample();

		LocalDateTime t1 = LocalDateTime.now();
		LocalTime zero = LocalTime.of(0,0,0);
		long num = service.findIdm(line);
		if(num == 0) {		
			Suica suica1 = new Suica();
			suica1.setIdm(line);
			suica1.setDate(t1);
			suica1.setState("登録");
			suica1.setTime(zero);
			repository.saveAndFlush(suica1);
		}else {
			Suica suica = new Suica();
			Suica state = service.find(line);
			LocalDateTime t2 = state.getDate();
			Duration t = Duration.between(t2,t1);
			int sec = (int)t.toSeconds();
			sec = 1500;
			int hour = sec / 3600;
			int min = (sec%3600) / 60;
			sec = sec % 60;
			LocalTime sumTime = LocalTime.of(hour,min,sec);
			if(state.getState().equals("出勤")) {
				suica.setIdm(line);
				suica.setState("退勤");
				suica.setTime(sumTime);
				mav.addObject("msg",line + "さんの退勤を受け付けました");
			}else {
				suica.setIdm(line);
				suica.setState("出勤");
				suica.setTime(zero);
				mav.addObject("msg",line + "さんの出勤を受け付けました");
			}
			suica.setDate(t1);
			repository.saveAndFlush(suica);
		}

		List<Suica> list = service.getAll();
		mav.addObject("datalist",list);
		return mav;
	}

}
