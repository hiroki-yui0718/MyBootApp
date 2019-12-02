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
	

	public LocalTime findScheStartTime(String name, LocalDate t) {
		long id = (long)entityManager.createQuery("select account_id from Account where username = :name").setParameter("name", name).getSingleResult();
		return (LocalTime)entityManager.createQuery("select scheStartTime from Management where account_id = :id and date = :str order by management_id desc").setParameter("id", id).setParameter("str", t).setMaxResults(1).getSingleResult(); 
	}
	public LocalTime findScheEndTime(String name, LocalDate d) {
		// TODO 自動生成されたメソッド・スタブ\
		long id = (long)entityManager.createQuery("select account_id from Account where username = :name").setParameter("name", name).getSingleResult();
		return (LocalTime)entityManager.createQuery("select scheEndTime from Management where account_id = :id and date = :str order by management_id desc").setParameter("id", id).setParameter("str", d).setMaxResults(1).getSingleResult(); 
	}


	public Management getLog() {
		// TODO 自動生成されたメソッド・スタブ
		return (Management)entityManager.createQuery("from Management order by management_id desc").setMaxResults(1).getSingleResult();
	}


	
}
