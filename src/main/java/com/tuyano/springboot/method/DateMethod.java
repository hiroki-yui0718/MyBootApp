package com.tuyano.springboot.method;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;

public class DateMethod {


	public LocalTime stringToTime(String str) {
		String[] ary = str.split(":");
		LocalTime t = LocalTime.of(Integer.valueOf(ary[0]),Integer.valueOf(ary[1]),0); 
		return t;
	}
	public String dateTimeToString(LocalDateTime t) {
		int hour = t.getHour();
		int min = t.getMinute();
		int sec = t.getSecond();
		String str = hour + ":" + min +":" + sec;
		return str;
	}
	public LocalDateTime setToDateTime(int year,int month,int i,String str) {
		String[] str2= str.split(":",0);
		LocalDateTime t = LocalDateTime.of(year,month,i,Integer.parseInt(str2[0]),Integer.parseInt(str2[1]),Integer.parseInt(str2[2]));
		return t; 
	}
	public LocalTime secToTime(int sec) {
		int hour = sec / 3600;
		int min = (sec%3600) / 60;
		sec = sec % 60;
		LocalTime str = LocalTime.of(hour, min,sec); 
		return str;
	}
	public int timeToSec(LocalTime t) {
		int sec;
		if(!(t ==null || t.equals(""))) {
		sec = t.getHour()* 3600;
		sec += t.getMinute() * 60;
		sec += t.getSecond();
		}else {
		sec = 0;
		}
		
		return sec;
	}


	public String secToString(int sec) {
		int hour = sec / 3600;
		int min = (sec%3600) / 60;
		sec = sec % 60;
		String str = hour + ":" + min +":" +sec; 
		return str;
	}
	public String setString(String monthTime,int sec) {
		String[] time = new String[3];
		time = monthTime.split(":", 0);
		int sum = Integer.parseInt(time[0]) * 3600;
		sum += Integer.parseInt(time[1]) * 60;
		sum += Integer.parseInt(time[2]);
		sum += sec;
		String str = secToString(sum);
		return str;
	}
	
	public LocalTime TimeNoDiff(LocalTime t1, LocalTime t2) {
		// TODO 自動生成されたメソッド・スタブ
		LocalTime t3 = null;

		try {
			if(!(t1.equals("") ||t2.equals(""))){
				Duration d = Duration.between(t2, t1);
				int sec = (int)(d.toSeconds());
				if(sec <0) {
					t3= secToTime(0);
				}else {
					t3 = secToTime(sec);
				}
			}else {
				t3 = null;
			}
		}catch(NullPointerException e) {
			t3 = null;
		}
		return t3;
	}
	public int TimeToSecDiff(LocalTime t1, LocalTime t2) {
		// TODO 自動生成さたメソッド・スタブ
		int sec;
		try{
			if(!(t1.equals("")||t2.equals(""))) {
				Duration d = Duration.between(t1, t2);
				sec = (int)d.toSeconds();
			}else {
				sec = 0;
			}
		}catch(NullPointerException e) {
			sec = 0;
		}
		return sec;
	}
}
