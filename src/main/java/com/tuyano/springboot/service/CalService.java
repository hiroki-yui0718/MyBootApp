package com.tuyano.springboot.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.tuyano.springboot.model.Cal;
import com.tuyano.springboot.model.Management;

@Service
public class CalService {
	@Autowired
	EntityManager entityManager;

	@Transactional
	@Modifying
	public int dropAll() {
		// TODO 自動生成されたメソッド・スタブ
		return (int)entityManager.createQuery("delete from Cal").executeUpdate();
		
	}
	public String getIdm(String name) {
		return (String)entityManager.createQuery("select idm from Account where username = :name").setParameter("name", name).getSingleResult();
	}
	@SuppressWarnings("unchecked")
	public List<Cal> getAll(String name,int year, String day1, String day2) {
		// TODO 自動生成されたメソッド・スタブ
		String idm = getIdm(name);
		return (List<Cal>)entityManager.createQuery("from Cal where idm = :idm and year = :year and  day between :day1 and :day2 ").setParameter("idm", idm).setParameter("year", year).setParameter("day1", day1).setParameter("day2", day2).getResultList(); 
	}
}