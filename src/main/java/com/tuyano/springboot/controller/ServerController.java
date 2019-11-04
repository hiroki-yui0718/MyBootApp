package com.tuyano.springboot.controller;

import java.time.*;
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

		try{
			mav.setViewName("server");
			ServerSide s1 = new ServerSide();
			String line = s1.runSample();
//			Suica suica1 = new Suica();
			LocalDateTime t1 = LocalDateTime.now();
//			suica1.setIdm(line);
//			suica1.setDate(t1);
//			suica1.setState("退勤");
//			suica1.setTime(0);
//			repository.saveAndFlush(suica1);
			Suica suica = new Suica();
			Suica state = service.find(line);
			LocalDateTime t2 = state.getDate();
			Duration t = Duration.between(t1,t2);
			long sumTime = t.toMinutes();
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
		}catch(HsqlException e) {
			System.out.println(e.getErrorCode());
		}

		List<Suica> list = service.getAll();
		mav.addObject("datalist",list);
		return mav;
	}

}
