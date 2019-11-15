package com.tuyano.springboot.controller;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tuyano.springboot.model.Management;
import com.tuyano.springboot.repositories.ManagementRepository;
import com.tuyano.springboot.service.ManagementService;

@Controller
public class ManagementController {

	@Autowired
	ManagementService service;

	@Autowired
	ManagementRepository repository;

	String[] week = {" ","日","月","火","水","木","金","土"};
	private int startDay;
	private int lastDate;
	private int year;
	private int month;

	public String date(LocalDateTime t) {
		int hour = t.getHour();
		int min = t.getMinute();
		int sec = t.getSecond();
		String str = hour + ":" + min +":" + sec;
		return str;
	}
	public String monthTime(int sec) {
		int hour = sec / 3600;
		int min = (sec%3600) / 60;
		sec = sec % 60;
		String str = hour + ":" + min +":" +sec; 
		return str;
	}
	public int sumTime(LocalTime t) {
		int sec = t.getHour()* 3600;
		sec += t.getMinute() * 60;
		sec += t.getSecond();
		return sec;
	}

	public void setCal() {
		Calendar cal = Calendar.getInstance();
		this.year = cal.get(Calendar.YEAR);
		this.month  = cal.get(Calendar.MONTH) + 1;
		cal.clear();
		// 月の初めの曜日を求めます。
		cal.set(year, month - 1, 1);// 引数: 1月: 0, 2月: 1, ...
		this.startDay = cal.get(Calendar.DAY_OF_WEEK);
		//月末の日付を求めます。
		cal.add(Calendar.MONTH, 1);
		cal.add(Calendar.DATE, -1);
		this.lastDate = cal.get(Calendar.DATE);

	}

	@RequestMapping("/calendar")
	public ModelAndView index(ModelAndView mav,Authentication authentication) {
		User userDetail = (User)authentication.getPrincipal();
		String name = userDetail.getUsername();
		setCal();
//		xここに削除文
		int j = startDay;
		for(int i = 1;i <= lastDate;i++) {
			Management manage = new Management();
			LocalDateTime t1 = LocalDateTime.of(year,month,i,0,0,0);
			LocalDateTime t2= LocalDateTime.of(year,month,i,23,59,59);
			String str = month + "月"+ i +"日";
			manage.setIdm(service.getIdm(name));
			manage.setYear(year);
			manage.setDay(str);

			manage.setWeek(week[j]);
			j++;
			if(j == 8)j = 1;
			manage.setScheStartTime(null);
			manage.setScheEndTime(null);

			try{
				manage.setStartTime(date(service.findStart(name,t1,t2)));
			}catch(NoResultException e) {
				manage.setStartTime(null);
			}
			try{
				manage.setEndTime(date(service.findEnd(name,t1,t2)));
			}catch(NoResultException e) {
				manage.setEndTime(null);
			}
			manage.setTime(null);
			try{
				List<LocalTime> m = service.findSumTime(name,t1,t2);
				int sum = 0;
				for(LocalTime t:m) {
					sum += sumTime(t);
				}
				manage.setSumTime(monthTime(sum));
			}catch(NoResultException e) {
				manage.setSumTime(null);
			}
			repository.saveAndFlush(manage);
		}
		mav.setViewName("calendar");
		mav.addObject("datalist",service.getAll(name,year,month,startDay,lastDate));
		return mav;
	}
}
