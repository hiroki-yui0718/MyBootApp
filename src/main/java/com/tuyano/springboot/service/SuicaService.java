package com.tuyano.springboot.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

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
	public long findSumTime(String idm,int month,int year) {
		return (long)entityManager.createQuery("select count(*) from Suica where idm = :idm and month = :month and year = :year")
				.setParameter("idm", idm).setParameter("month", month).setParameter("year", year).getSingleResult();
	}
	public String findName(String idm) {
		return (String)entityManager.createQuery("select username from Account where idm = :idm").setParameter("idm", idm).getSingleResult();
	}
	public int getSumTime(String idm,int month,int year,int day,int sec) {
		// TODO 自動生成されたメソッド・スタブ
		return (int)entityManager.createQuery("select monthTime from Suica WHERE idm = :idm and month = :month and year = :year") 
		.setParameter("sec",sec).setParameter("idm", idm).setParameter("month", month).setParameter("year", year).getSingleResult();
		
	}
}
