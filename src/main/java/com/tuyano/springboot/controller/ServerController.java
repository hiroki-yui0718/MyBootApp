package com.tuyano.springboot.controller;
import java.time.*;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tuyano.springboot.method.DateMethod;
import com.tuyano.springboot.model.Account;
import com.tuyano.springboot.model.Data;
import com.tuyano.springboot.repositories.DataRepository;
import com.tuyano.springboot.service.AccountService;
import com.tuyano.springboot.service.DataService;
import com.tuyano.springboot.socket.*;
@Controller
public class ServerController {

	@Autowired
	DataRepository repository;

	@Autowired
	DataService service;
	@Autowired
	AccountService service2;

	LocalDate d1 = LocalDate.now();
	LocalTime t1 = LocalTime.now();
	DateMethod m = new DateMethod();
	
	@RequestMapping("/server")
	public ModelAndView server(ModelAndView mav) {
		ClientSide c = new ClientSide();
//		mav.setViewName("server");
		ServerSide s1 = new ServerSide();
		String line = s1.runSample();
		Data data = new Data();

		long num = service.findIdm(line);
		if(num == 0) {
			c.runSample("登録");
			data.setIdm(line);
			data.setState("登録");
			data.setDaySumTime(m.secToTime(0));
		}else {
			Data state = service.find(line);
			LocalTime t2 = state.getTime();
			Duration t = Duration.between(t2,t1);
			int sec = (int)t.toSeconds();

			if(state.getState().equals("出勤")) {
				data.setIdm(line);
				data.setState("退勤");
				c.runSample("退勤");
				LocalTime t3 = service.findSumDayTime(line);
				t3.plus(Duration.ofSeconds(sec));
				data.setDaySumTime(t3);
			}else {
				data.setIdm(line);
				data.setState("出勤");
				data.setDaySumTime(m.secToTime(0));
				c.runSample("出勤");
			}
		}
		data.setDate(d1);
		data.setTime(t1);
		Account account = service2.findAccount(line);
		data.setAccount(account);
		repository.saveAndFlush(data);
		mav = new ModelAndView("redirect:/");
		return mav;
	}

}
