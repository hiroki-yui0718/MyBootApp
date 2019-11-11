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
	public LocalTime dayTime(int sec) {
		int hour = sec / 3600;
		int min = (sec%3600) / 60;
		sec = sec % 60;
		return LocalTime.of(hour,min,sec);
	}
	public String monthTime(int sec) {
		int hour = sec / 3600;
		int min = (sec%3600) / 60;
		sec = sec % 60;
		String str = hour + ":" + min +":" +sec; 
		return str;
	}
	public String sumTime(String monthTime,int sec) {
		String[] time = new String[3];
		time = monthTime.split(":", 0);
		int sum = Integer.parseInt(time[0]) * 3600;
		sum += Integer.parseInt(time[1]) * 60;
		sum += Integer.parseInt(time[2]);
		sum += sec;
		String str = monthTime(sum);
		return str;
	}
	@RequestMapping("/server")
	public ModelAndView server(ModelAndView mav) {
		ClientSide c = new ClientSide();
		mav.setViewName("server");
		ServerSide s1 = new ServerSide();
		String line = s1.runSample();
		String name =service.findName(line);
		Suica suica = new Suica();
		LocalDateTime t1 = LocalDateTime.now();
		long num = service.findIdm(line);
		if(num == 0) {
			c.runSample("登録");
			suica.setIdm(line);
			suica.setDate(t1);
			suica.setState("登録");
			suica.setDayTime(dayTime(0));
			suica.setMonthTime(monthTime(0));
			mav.addObject("msg",name + "さんの登録を受け付けました");
		}else {
			Suica state = service.find(line);
			LocalDateTime t2 = state.getDate();
			Duration t = Duration.between(t2,t1);
			int sec = (int)t.toSeconds();

			if(state.getState().equals("出勤")) {
				suica.setIdm(line);
				suica.setState("退勤");
				c.runSample("退勤");
				mav.addObject("msg",name + "さんの退勤を受け付けました");
				//ｘ初退勤じゃない時
				if(service.findState(line) != 0) {
					int month = service.findMonth(line);
					if(t1.getMonthValue() != month) { //ｘ月初め
						suica.setDayTime(dayTime(sec));
						suica.setMonthTime(monthTime(sec));
					}else{ //ｘ日初め or 同日
						String m = service.findMonthTime(line);
						suica.setDayTime(dayTime(sec));
						suica.setMonthTime(sumTime(m,sec));
					}
				}else {
					suica.setDayTime(dayTime(sec));
					suica.setMonthTime(monthTime(sec));
				}

			}else {
				suica.setIdm(line);
				suica.setState("出勤");
				suica.setDayTime(dayTime(0));
				suica.setMonthTime(monthTime(0));
				c.runSample("出勤");
				mav.addObject("msg",name + "さんの出勤を受け付けました");
			}
			suica.setDate(t1);



		}
		Account account = service.findAccount(line);
		suica.setAccount(account);
		repository.saveAndFlush(suica);
		List<Suica> list = service.getAll();
		mav.addObject("datalist",list);
		mav.addObject("if",dayTime(0));
		return mav;
	}

}
