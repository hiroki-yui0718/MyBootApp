package com.tuyano.springboot.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import com.tuyano.springboot.model.Account;
import com.tuyano.springboot.model.Suica;
@Service
public class SuicaService {
	@PersistenceContext
	EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<Suica> getAll() {
		return (List<Suica>)entityManager.createQuery("from Suica order by id desc").getResultList();
	}
	public long findIdm(String idm) {
		return (long)entityManager.createQuery("select count(*) from Suica where idm = :idm").setParameter("idm", idm).getSingleResult();
	}
	public long findTime(String idm) {
		return (long)entityManager.createQuery("select sum(time) from Suica where idm = :idm").setParameter("idm", idm).getSingleResult();
	}
	public Suica find(String idm) {
		return (Suica)entityManager.createQuery("from Suica where idm = :idm order by id desc").setParameter("idm", idm).setMaxResults(1).getSingleResult();
	}
	public int findMonth(String idm) {
		LocalDateTime d =  (LocalDateTime)entityManager.createQuery("select date from Suica where idm = :idm and state = :state order by id desc").setParameter("idm", idm).setParameter("state", "退勤").setMaxResults(1).getSingleResult();
		return d.getMonthValue();
	}
	public LocalTime findMonthTime(String idm) {
		// TODO 自動生成されたメソッド・スタブ
		 return (LocalTime) entityManager.createQuery("select monthTime from Suica where idm = :idm and state = :state order by id desc").setParameter("idm", idm).setParameter("state", "退勤").setMaxResults(1).getSingleResult();
	}
	public long findState(String idm) {
		return (long)entityManager.createQuery("select count(*) from Suica where idm = :idm and state = :state").setParameter("idm", idm).setParameter("state", "退勤").getSingleResult();
	}
	public Account findAccount(String idm) {
		// TODO 自動生成されたメソッド・スタブ
		return (Account)entityManager.createQuery("from Account where idm = :idm").setParameter("idm", idm).getSingleResult();
	}

}
