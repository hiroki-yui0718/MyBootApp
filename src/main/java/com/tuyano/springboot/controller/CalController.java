package com.tuyano.springboot.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.List;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tuyano.springboot.method.DateMethod;
import com.tuyano.springboot.model.Account;
import com.tuyano.springboot.model.Cal;
import com.tuyano.springboot.model.Data;
import com.tuyano.springboot.model.Management;
import com.tuyano.springboot.repositories.CalRepository;
import com.tuyano.springboot.repositories.DataRepository;
import com.tuyano.springboot.repositories.ManagementRepository;
import com.tuyano.springboot.service.AccountService;
import com.tuyano.springboot.service.CalService;
import com.tuyano.springboot.service.DataService;
import com.tuyano.springboot.service.ManagementService;
import com.tuyano.springboot.service.ReservationUserDetailsService;

@Controller
public class CalController {
	String[] week = {" ","日","月","火","水","木","金","土"};
	private int startDay;
	private int lastDate;
	private int year;
	private int month;

	DateMethod m = new DateMethod();

	@Autowired
	ManagementService service;
	@Autowired
	ReservationUserDetailsService service2;
	@Autowired
	AccountService service3;
	@Autowired
	CalService service4;
	@Autowired 
	DataService service5;

	@Autowired
	CalRepository repository;
	@Autowired
	ManagementRepository repository2;
	

	
	@RequestMapping(value="/calendar/{id}/{date}",method=RequestMethod.GET)
	public ModelAndView send(@PathVariable int date,@PathVariable long id,HttpServletRequest request,HttpSession session,ModelAndView mav,Authentication authentication){
		String name = service3.findName(id);
		mav.addObject("date",date);
		setCal(date);
		//		xここに削除文
		service4.dropAll();
		int j = startDay;
		int sumTime = 0;
		int sumTime2 = 0;
		for(int i = 1;i <= lastDate;i++) {
			Cal cal = new Cal();
			LocalDate d = LocalDate.of(year,month,i);
			String str = month + "月"+ i +"日";
			cal.setIdm(service4.getIdm(name));
			cal.setYear(year);
			System.out.println(str);
			cal.setDay(str);

			cal.setWeek(week[j]);
			j++;
			if(j == 8)j = 1;
			LocalTime t1 = null;
			LocalTime t2 = null;
			LocalTime t5 =null;
			LocalTime t6 =null;
			try{
				t5 = service.findScheStartTime(name,d);
				cal.setScheStartTime(t5);

			}catch(NoResultException e) {
				cal.setScheStartTime(null);
			}
			try{
				t1 = service.findScheEndTime(name,d);
				cal.setScheEndTime(t1);
			}catch(NoResultException e) {
				cal.setScheEndTime(null);
			}
			try{
				LocalTime t7 = service5.findStart(name,d);
				cal.setStartTime(t7);
			}catch(NoResultException e) {
				cal.setStartTime(null);
			}
			try{
				t2 = service5.findEnd(name,d);
				cal.setEndTime(t2);
			}catch(NoResultException e) {
				cal.setEndTime(null);
			}
			try{
				LocalTime t4 = m.TimeNoDiff(t1,t2);
				cal.setOverTime(t4);
			}catch(NoResultException e) {
				cal.setOverTime(null);
			}
			try{
				t6 = service5.findDaySumTime(name,d);
				cal.setDaySumTime(t6);
			}catch(NoResultException e) {
				cal.setDaySumTime(null);
			}
				
			sumTime += m.TimeToSecDiff(t5,t1);
			sumTime2 += m.timeToSec(t6);
			
			repository.saveAndFlush(cal);
		}
		String scheSumTime = m.secToString(sumTime);
		String monthSumTime = m.secToString(sumTime2);
		mav.addObject("next",date+1);
		mav.addObject("back",date-1);
		mav.addObject("name",year + "年　　    "+name +"さんの勤怠管理表です");
		if(scheSumTime == null) {
			mav.addObject("sche","勤務予定時間は未入力です。");
		}else {
			mav.addObject("sche","勤務予定時間は" + scheSumTime + "です。");
		}
		if(scheSumTime == null) {
			mav.addObject("time","総勤務時間は未入力です。");
		}else {
			mav.addObject("time","総勤務時間は" + monthSumTime + "です。");
		}
		mav.setViewName("calendar");
		mav.addObject("id",id);
		int num = startDay;
		mav.addObject("datalist",service4.getAll(name,year,num));
		return mav;
	}
	@RequestMapping(value="/calendar/{id}",method=RequestMethod.POST)
	public ModelAndView index(@PathVariable long id,ModelAndView mav,HttpServletRequest request,Authentication authentication){

		String name = service3.findName(id);
		int num = Integer.parseInt(request.getParameter("date"));
		setCal(num);
		for(int i = 1;i <= lastDate;i++) {
			Management management = new Management();
			LocalDate d = LocalDate.of(year,month,i);
			LocalDateTime t2 = LocalDateTime.now();
			User userDetail = (User)authentication.getPrincipal();
			String str = userDetail.getUsername();
			Account account = service3.findAll(str);

			management.setName(name);
			management.setDate(d);
			management.setScheStratTime(m.stringToTime(request.getParameter("scheStartTime" + i)));
			management.setScheEndTime(m.stringToTime(request.getParameter("scheEndTime" + i)));
			management.setCreatedTime(t2);
			management.setAccount(account);
			repository2.saveAndFlush(management);

			try {
				LocalTime t3 = m.stringToTime(request.getParameter("startTime" + i));
				LocalTime t4 = m.stringToTime(request.getParameter("endTime" + i));
				if(!(t3.equals("")||t3 ==null)){
					service5.startUpdate(t3,d);
				}

				if(!(t3.equals("")||t3==null)) {
					service5.endUpdate(t4,d);
				}
			}catch(NullPointerException e) {
				e.printStackTrace();
			}catch(NoResultException e){
				e.printStackTrace();
			}
		}
		mav.setViewName("calendar");
		mav = new ModelAndView("redirect:/calendar/{id}/" + num);
		return mav;
	}
	public void setCal(int num) {
		Calendar cal = Calendar.getInstance();
		this.month  = cal.get(Calendar.MONTH) + 1+ num;

		int i = 0;
		while(true) {
			if(month > 12) { //NEXT
				this.month -= 12;
				i++;
			}if(month < 1){ //BACK
				this.month += 12;
				i--;
			}else if(0 < month && 13 > month){
				break;
			}
		}
		this.year = cal.get(Calendar.YEAR) + i;

		cal.clear();
		cal.set(year, month - 1, 1);
		this.startDay = (cal.get(Calendar.DAY_OF_WEEK));

		cal.add(Calendar.MONTH, 1);
		cal.add(Calendar.DATE, -1);
		this.lastDate = (cal.get(Calendar.DATE));

	}

}
