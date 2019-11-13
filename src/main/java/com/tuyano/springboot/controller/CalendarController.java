package com.tuyano.springboot.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tuyano.springboot.model.Account;
import com.tuyano.springboot.service.CalendarService;

@Controller
public class CalendarController {
	
	@Autowired
	CalendarService service;
	
	String[] week = {" ","日","月","火","水","木","金","土"};
	private int startDay;
	private int lastDate;
	private int month;
	
	public void setCal() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
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

	public List<String> week(){
		List<String> list = new ArrayList<>(); 
		setCal();
		int i = 1;
		int j = startDay;
		
		loop: while(true) {
			while(j < week.length) {
				list.add(week[j]);
				i++;
				j++;
				if(j == 8)j = 1;
				if(i > lastDate) {
					break loop;

				}

			}
		}
		return list;
	}
	public List<String> day(){
		List<String> list = new ArrayList<>();
		for(int j = 1;j < lastDate + 1;j++) {
			String str = month + "月"+ j +"日";
			list.add(str);
		}
		return list;
	}
	
	@RequestMapping("/calendar")
	public ModelAndView index(ModelAndView mav,Authentication authentication) {
		User userDetail = (User)authentication.getPrincipal();
        String name = userDetail.getUsername();
        System.out.println(name);
//		service.findSumTime();
		mav.setViewName("calendar");
		mav.addObject("day",day());
		mav.addObject("week",week());
//		mav.addObject("SumTIme")
		return mav;
	}
}
