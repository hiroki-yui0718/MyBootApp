package com.tuyano.springboot.service;

import java.time.LocalTime;
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
	public List<Cal> getAll(String name,int year, int day) {
		// TODO 自動生成されたメソッド・スタブ
		String idm = getIdm(name);
		return (List<Cal>)entityManager.createQuery("from Cal where idm = :idm and year = :year and  day LIKE :day ").setParameter("idm", idm).setParameter("year", year).setParameter("day", day + "%").getResultList(); 
	}
	public long getId(String name) {
		// TODO 自動生成されたメソッド・スタブ
		return (long)entityManager.createQuery("from management_id from Management where name = :name").setParameter("name", name).getSingleResult();
	}
	@Transactional
	@Modifying
	public void scheStartUpdate(long id, LocalTime t) {
		// TODO 自動生成されたメソッド・スタブ
		entityManager.createQuery("Update Management set scheStartTime = :t where management_id = :id").setParameter("t", t).setParameter("id", id).executeUpdate();
	}
	@Transactional
	@Modifying
	public void scheEndUpdate(long id, LocalTime t) {
		// TODO 自動生成されたメソッド・スタブ
		entityManager.createQuery("Update Management set scheEndTime = :t where management_id = :id").setParameter("t", t).setParameter("id", id).executeUpdate();

	}
}