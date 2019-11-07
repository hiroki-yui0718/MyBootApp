package com.tuyano.springboot.controller;
import java.time.*;
import java.util.List;

import javax.annotation.PostConstruct;

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
	public LocalTime date(int sec) {
		int hour = sec / 3600;
		int min = (sec%3600) / 60;
		sec = sec % 60;
		return LocalTime.of(hour,min,sec);
	}
	@RequestMapping("/server")
	public ModelAndView server(ModelAndView mav) {

		mav.setViewName("server");
		ServerSide s1 = new ServerSide();
		String line = s1.runSample();
		Suica suica = new Suica();
		LocalDateTime t1 = LocalDateTime.now();
		long num = service.findIdm(line);
		if(service.findName(line) != null) {
			String name = service.findName(line);
			mav.addObject("name",name);
		}else {
			mav.addObject("name","-----");
		}
		if(num == 0) {		
			suica.setIdm(line);
			suica.setDate(t1);
			suica.setState("登録");
			suica.setYear(t1.getDayOfYear());
			suica.setMonth(t1.getDayOfMonth());
			suica.setDayTime(date(0));
			suica.setMonthTime(date(0));
		}else {
			Suica state = service.find(line);
			LocalDateTime t2 = state.getDate();
			Duration t = Duration.between(t2,t1);
			int sec = (int)t.toSeconds();

			if(state.getState().equals("出勤")) {
				suica.setIdm(line);
				suica.setState("退勤");
		
				mav.addObject("msg",line + "さんの退勤を受け付けました");
				long su = service.findSumTime(line,t1.getDayOfYear(),t1.getDayOfMonth());
				if(su == 0) {
					suica.setYear(t1.getDayOfYear());
					suica.setMonth(t1.getDayOfMonth());
					suica.setDayTime(date(sec));
					suica.setMonthTime(date(sec));
				}else {
					int sum = service.getSumTime(line,t1.getYear(),t1.getMonthValue(),t1.getDayOfMonth(),sec);
					suica.setYear(t1.getDayOfYear());
					suica.setMonth(t1.getDayOfMonth());
					suica.setDayTime(date(sec));
					suica.setMonthTime(date(sec + sum));
				}
			}else {
				suica.setIdm(line);
				suica.setState("出勤");
				suica.setYear(t1.getDayOfYear());
				suica.setMonth(t1.getDayOfMonth());
				suica.setDayTime(date(0));
				suica.setMonthTime(date(0));
				mav.addObject("msg",line + "さんの出勤を受け付けました");
			}
			suica.setDate(t1);
			
			

		}
		repository.saveAndFlush(suica);
		List<Suica> list = service.getAll();
		mav.addObject("datalist",list);
		mav.addObject("if",date(0));
		return mav;
	}

}
