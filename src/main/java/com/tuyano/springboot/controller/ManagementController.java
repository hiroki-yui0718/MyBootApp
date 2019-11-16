package com.tuyano.springboot.controller;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TransactionRequiredException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tuyano.springboot.model.Account;
import com.tuyano.springboot.model.Management;
import com.tuyano.springboot.model.Manager;
import com.tuyano.springboot.repositories.ManagementRepository;
import com.tuyano.springboot.repositories.ManagerRepository;
import com.tuyano.springboot.service.ManagementService;

@Controller
public class ManagementController {

	@Autowired
	ManagementService service;

	@Autowired
	ManagementRepository repository;
	@Autowired
	ManagerRepository repository2;

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
	@RequestMapping(value="/calendar",method=RequestMethod.POST)
	public ModelAndView index(ModelAndView mav,HttpServletRequest request,Authentication authentication){
		User userDetail = (User)authentication.getPrincipal();
		String name = userDetail.getUsername();

		setCal();
		for(int i = 1;i <= lastDate;i++) {
			Manager mana = new Manager();
			LocalDate t = LocalDate.of(year,month,i);
			mana.setIdm(service.getIdm(name));
			mana.setDate(t);
			String str1 = request.getParameter("scheStartTime" + i);
			String str2 = request.getParameter("scheEndTime" + i);
			mana.setScheStratTime(str1);
			mana.setScheEndTime(str2);
			repository2.saveAndFlush(mana);
		}
		mav.setViewName("calendar");
		mav = new ModelAndView("redirect:/calendar");
		return mav;
	}
	@RequestMapping(value="/calendar",method=RequestMethod.GET)
	public ModelAndView send(ModelAndView mav,Authentication authentication){
		User userDetail = (User)authentication.getPrincipal();
		String name = userDetail.getUsername();
		setCal();
		//		xここに削除文
		service.delAll();
		int j = startDay;
		for(int i = 1;i <= lastDate;i++) {
			Management manage = new Management();
			LocalDateTime t1 = LocalDateTime.of(year,month,i,0,0,0);
			LocalDateTime t2= LocalDateTime.of(year,month,i,23,59,59);
			LocalDate t3 = LocalDate.of(year,month,i);
			String str = month + "月"+ i +"日";
			manage.setIdm(service.getIdm(name));
			manage.setYear(year);
			manage.setDay(str);

			manage.setWeek(week[j]);
			j++;
			if(j == 8)j = 1;
			LocalDateTime t4 = null;
			String str2 = null;
			try{
				String str1 = service.findScheStartTime(name,t3);
				manage.setScheStartTime(str1);
				
			}catch(NoResultException e) {
				manage.setScheStartTime(null);
			}
			try{
				str2 = service.findScheEndTime(name,t3);
				manage.setScheEndTime(str2);
			}catch(NoResultException e) {
				manage.setScheEndTime(null);
			}
			try{
				manage.setStartTime(date(service.findStart(name,t1,t2)));
			}catch(NoResultException e) {
				manage.setStartTime(null);
			}
			try{
				t4 = service.findEnd(name,t1,t2);
				manage.setEndTime(date(t4));
			}catch(NoResultException e) {
				manage.setEndTime(null);
			}
			try{
				String str3 = diff(t4,str2);
				manage.setTime(str3);
			}catch(NoResultException e) {
				manage.setTime(null);
			}
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
		mav.addObject("name",name +"さんの勤怠管理表です");
		mav.setViewName("calendar");
		mav.addObject("datalist",service.getAll(name,year,month,startDay,lastDate));
		return mav;
	}
	public String diff(LocalDateTime t4, String str2) {
		// TODO 自動生成されたメソッド・スタブ
		String str1;
		try {
		String[] str = str2.split(":",0);
		LocalTime t = LocalTime.of(t4.getHour(),t4.getMinute(),t4.getMinute()); 
		LocalTime t2 = LocalTime.of(Integer.valueOf(str[0]), Integer.valueOf(str[1]),0);
		Duration d = Duration.between(t2, t);
		if(d.toSeconds() <0) {
			str1 = "-" + monthTime((int)-d.toSeconds());
		}else {
			str1 = monthTime((int)d.toSeconds());
		}
		}catch(NullPointerException e) {
			str1 = null;
		}
		return str1;
	}
}
