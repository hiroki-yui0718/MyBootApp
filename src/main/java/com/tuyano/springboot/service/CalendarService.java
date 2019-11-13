package com.tuyano.springboot.service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CalendarService {
	@Autowired
	EntityManager entityManager;
	
	public LocalTime findSumTime(String name,LocalDateTime str1,LocalDateTime str2) {
		// TODO 自動生成されたメソッド・スタブ
		String idm = (String) entityManager.createQuery("select idm from Account where username = :name").setParameter("name", name).getSingleResult();
		return (LocalTime)entityManager.createQuery("select dayTime from Suica where idm = :idm and state = :state and date between :str1 and :str2 order by id desc").setParameter("idm", idm).setParameter("state", "退勤").setParameter("str1", str1).setParameter("str2", str2).setMaxResults(1).getSingleResult(); 

	}
	public LocalDateTime findStart(String name,LocalDateTime str1,LocalDateTime str2) {
		// TODO 自動生成されたメソッド・スタブ
		String idm = (String) entityManager.createQuery("select idm from Account where username = :name").setParameter("name", name).getSingleResult();
		return (LocalDateTime)entityManager.createQuery("select date from Suica where idm = :idm and state = :state and date between :str1 and :str2 order by id desc").setParameter("idm", idm).setParameter("state", "出勤").setParameter("str1", str1).setParameter("str2", str2).setMaxResults(1).getSingleResult(); 

	}
	
}
