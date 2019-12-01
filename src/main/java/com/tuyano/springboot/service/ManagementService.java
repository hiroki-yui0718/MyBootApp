package com.tuyano.springboot.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.tuyano.springboot.model.Cal;
import com.tuyano.springboot.model.Management;


@Service
public class ManagementService {
	@Autowired
	EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<Management> getAll(String name,int year, int month,int startDay,int endDay) {
		// TODO 自動生成されたメソッド・スタブ
		String idm = getIdm(name);
		return (List<Management>)entityManager.createQuery("from Management where idm = :idm").setParameter("idm", idm).getResultList(); 
	}

	public LocalTime findScheStartTime(String name, LocalDate t) {
		return (LocalTime)entityManager.createQuery("select scheStartTime from Management where name = :name and date = :str order by management_id desc").setParameter("name", name).setParameter("str", t).setMaxResults(1).getSingleResult(); 
	}
	public LocalTime findScheEndTime(String name, LocalDate t) {
		// TODO 自動生成されたメソッド・スタブ\
		return (LocalTime)entityManager.createQuery("select scheEndTime from Management where name = :name and date = :str order by managemnt_id desc").setParameter("name", name).setParameter("str", t).setMaxResults(1).getSingleResult(); 
	}


	public Management getLog() {
		// TODO 自動生成されたメソッド・スタブ
		return (Management)entityManager.createQuery("from Management order by management_id desc").setMaxResults(1).getSingleResult();
	}
	public String getIdm(String name) {
		return (String)entityManager.createQuery("select idm from Account where username = :name").setParameter("name", name).getSingleResult();
	}

	
}
