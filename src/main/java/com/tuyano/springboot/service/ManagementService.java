package com.tuyano.springboot.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.tuyano.springboot.model.Account;
import com.tuyano.springboot.model.Management;


@Service
public class ManagementService {
	@Autowired
	EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<LocalTime> findSumTime(String name,LocalDateTime str1,LocalDateTime str2) {
		// TODO 自動生成されたメソッド・スタブ
		String idm = getIdm(name);
		return (List<LocalTime>)entityManager.createQuery("select dayTime from Suica where idm = :idm and state = :state and date between :str1 and :str2").setParameter("idm", idm).setParameter("state", "退勤").setParameter("str1", str1).setParameter("str2", str2).getResultList(); 

	}
	public LocalDateTime findStart(String name,LocalDateTime str1,LocalDateTime str2) {
		// TODO 自動生成されたメソッド・スタブ
		String idm = getIdm(name);
		return (LocalDateTime)entityManager.createQuery("select date from Suica where idm = :idm and state = :state and date between :str1 and :str2 order by id").setParameter("idm", idm).setParameter("state", "出勤").setParameter("str1", str1).setParameter("str2", str2).setMaxResults(1).getSingleResult(); 

	}
	public LocalDateTime findEnd(String name, LocalDateTime t1, LocalDateTime t2) {
		// TODO 自動生成されたメソッド・スタブ
		String idm = getIdm(name);
		return (LocalDateTime)entityManager.createQuery("select date from Suica where idm = :idm and state = :state and date between :str1 and :str2 order by id desc").setParameter("idm", idm).setParameter("state", "退勤").setParameter("str1", t1).setParameter("str2", t2).setMaxResults(1).getSingleResult(); 
	}
	@SuppressWarnings("unchecked")
	public List<Management> getAll(String name,int year, int month,int startDay,int endDay) {
		// TODO 自動生成されたメソッド・スタブ
		String idm = getIdm(name);
		return (List<Management>)entityManager.createQuery("from Management where idm = :idm").setParameter("idm", idm).getResultList(); 
	}
	public String getIdm(String name) {
		return (String)entityManager.createQuery("select idm from Account where username = :name").setParameter("name", name).getSingleResult();
	}
	@Transactional
	@Modifying
	public int dropAll() {
		// TODO 自動生成されたメソッド・スタブ
		return (int)entityManager.createQuery("delete from Management").executeUpdate();
		
	}
	public String findScheStartTime(String name, LocalDate t) {
		// TODO 自動生成されたメソッド・スタブ
		String idm = getIdm(name);
		return (String)entityManager.createQuery("select scheStartTime from Manager where idm = :idm and date = :str order by id desc").setParameter("idm", idm).setParameter("str", t).setMaxResults(1).getSingleResult(); 
	}
	public String findScheEndTime(String name, LocalDate t) {
		// TODO 自動生成されたメソッド・スタブ
		String idm = getIdm(name);
		return (String)entityManager.createQuery("select scheEndTime from Manager where idm = :idm and date = :str order by id desc").setParameter("idm", idm).setParameter("str", t).setMaxResults(1).getSingleResult(); 
	}
	@SuppressWarnings("unchecked")
	public List<Account> findDataAll(){
		return (List<Account>)entityManager.createQuery("from Account").getResultList(); 
	}
	
}
