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
import com.tuyano.springboot.model.SumTime;
import com.tuyano.springboot.repositories.SuicaRepository;
import com.tuyano.springboot.repositories.SumTimeRepository;
import com.tuyano.springboot.service.SuicaService;
import com.tuyano.springboot.socket.*;
@Controller
public class ServerController {

	@Autowired
	SuicaRepository repository;
	@Autowired
	SumTimeRepository repository2;

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
		SumTime st  = new SumTime();
		LocalDateTime t1 = LocalDateTime.now();
		long num = service.findIdm(line);

		if(num == 0) {		
			st.setIdm(line);
			st.setYear(t1.getDayOfYear());
			st.setMonth(t1.getDayOfMonth());
			st.setSumTime(0);
			repository2.saveAndFlush(st);
			suica.setIdm(line);
			suica.setDate(t1);
			suica.setState("登録");
			suica.setTime(null);
			mav.addObject("sumTime","----");
		}else {
			Suica state = service.find(line);
			LocalDateTime t2 = state.getDate();
			Duration t = Duration.between(t2,t1);
			int sec = (int)t.toSeconds();

			if(state.getState().equals("出勤")) {
				suica.setIdm(line);
				suica.setState("退勤");
				suica.setTime(date(sec));
				mav.addObject("msg",line + "さんの退勤を受け付けました");
				long su = service.findSumTime(line,t1.getDayOfYear(),t1.getDayOfMonth());
				if(su == 0) {
					st.setIdm(line);
					st.setYear(t1.getDayOfYear());
					st.setMonth(t1.getDayOfMonth());
					st.setSumTime(sec);
					repository2.saveAndFlush(st);
					mav.addObject("sumTime",sec);
				}else {
					int sum = service.getPlusTime(line,t1.getDayOfYear(),t1.getDayOfMonth(),sec);
					mav.addObject("sumTime",date(sum));
				}
			}else {
				suica.setIdm(line);
				suica.setState("出勤");
				suica.setTime(null);
				mav.addObject("msg",line + "さんの出勤を受け付けました");
				mav.addObject("sumTime","----");
			}
			suica.setDate(t1);

		}
		repository.saveAndFlush(suica);
		List<Suica> list = service.getAll();
		mav.addObject("datalist",list);
		return mav;
	}

}
