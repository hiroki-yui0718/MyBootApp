package com.tuyano.springboot.controller;
import java.time.*;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tuyano.springboot.model.Account;
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
		if(num == 0) {		
			suica.setIdm(line);
			suica.setDate(t1);
			suica.setState("登録");
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
				//初退勤じゃない時
				if(service.findState(line) != 0) {
					int month = service.findMonth(line);
					if(t1.getMonthValue() != month) { //月初め
						suica.setDayTime(date(sec));
						suica.setMonthTime(date(sec));
					}else{ //日初め or 同日
						LocalTime m = service.findMonthTime(line);
						int mSumTime = m.getSecond() + sec;
						suica.setDayTime(date(sec));
						suica.setMonthTime(date(mSumTime));
					}
				}else {
					suica.setDayTime(date(sec));
					suica.setMonthTime(date(sec));
				}

			}else {
				suica.setIdm(line);
				suica.setState("出勤");
				suica.setDayTime(date(0));
				suica.setMonthTime(date(0));
				mav.addObject("msg",line + "さんの出勤を受け付けました");
			}
			suica.setDate(t1);



		}
		Account account = service.findAccount(line);
		suica.setAccount(account);
		repository.saveAndFlush(suica);
		List<Suica> list = service.getAll();
		mav.addObject("datalist",list);
		mav.addObject("if",date(0));
		return mav;
	}

}
