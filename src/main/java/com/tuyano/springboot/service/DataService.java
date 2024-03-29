package com.tuyano.springboot.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.tuyano.springboot.model.Data;
@Service
public class DataService {
	@PersistenceContext
	EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<Data> getAll() {
		return (List<Data>)entityManager.createQuery("from Data where bool = :bool order by data_id desc").setParameter("bool", true).getResultList();
	}
	public long findIdm(String idm) {
		return (long)entityManager.createQuery("select count(*) from Data where idm = :idm").setParameter("idm", idm).getSingleResult();
	}
	public long findTime(String idm) {
		return (long)entityManager.createQuery("select sum(time) from Data where idm = :idm").setParameter("idm", idm).getSingleResult();
	}
	public Data find(String idm) {
		return (Data)entityManager.createQuery("from Data where idm = :idm order by data_id desc").setParameter("idm", idm).setMaxResults(1).getSingleResult();
	}
	public int findMonth(String idm) {
		LocalDateTime d =  (LocalDateTime)entityManager.createQuery("select date from Data where idm = :idm and state = :state order by suica_id desc").setParameter("idm", idm).setParameter("state", "退勤").setMaxResults(1).getSingleResult();
		return d.getMonthValue();
	}
	public String findMonthTime(String idm) {
		// TODO 自動生成されたメソッド・スタブ
		return (String)entityManager.createQuery("select monthTime from Data where idm = :idm and state = :state order by suica_id desc").setParameter("idm", idm).setParameter("state", "退勤").setMaxResults(1).getSingleResult();
	}
	public long findState(String idm) {
		return (long)entityManager.createQuery("select count(*) from Data where idm = :idm and state = :state").setParameter("idm", idm).setParameter("state", "退勤").getSingleResult();
	}

	@Transactional
	@Modifying
	public void startUpdate(long id, LocalTime t, LocalDate d) {
		// TODO 自動生成されたメソッド・スタブ
		entityManager.createQuery("update Data set time = :time where data_id = :id").setParameter("time", t).setParameter("id",id).executeUpdate();
		}
	@Transactional
	@Modifying
	public void endUpdate(long id, LocalTime t, LocalDate d) {
		// TODO 自動生成されたメソッド・スタブ
		entityManager.createQuery("update Data set time = :time where data_id = :id").setParameter("time", t).setParameter("id",id).executeUpdate();
	}
	public LocalTime findSumDayTime(String line) {
		// TODO 自動生成されたメソッド・スタブ
		return (LocalTime)entityManager.createQuery("select daySumTime from Data where state = :state and idm = :line order by data_id desc").setParameter("state", "退勤").setParameter("line", line).setMaxResults(1).getSingleResult();
	}
	public LocalTime findStart(String name,LocalDate d) {
		// TODO 自動生成されたメソッド・スタブ
		long id = (long)entityManager.createQuery("select account_id from Account where username = :name").setParameter("name", name).getSingleResult();
		return (LocalTime)entityManager.createQuery("select time from Data where account_id = :id and state = :state and date = :date order by data_id").setParameter("id", id).setParameter("state", "出勤").setParameter("date", d).setMaxResults(1).getSingleResult(); 

	}
	public LocalTime findEnd(String name, LocalDate d) {
		// TODO 自動生成されたメソッド・スタブ
		long id = (long)entityManager.createQuery("select account_id from Account where username = :name").setParameter("name", name).getSingleResult();
		return (LocalTime)entityManager.createQuery("select time from Data where account_id = :id and state = :state and date = :date order by data_id desc").setParameter("id", id).setParameter("state", "退勤").setParameter("date", d).setMaxResults(1).getSingleResult(); 
	}
	public LocalTime findDaySumTime(String name,LocalDate d) {
		// TODO 自動生成されたメソッド・スタブ
		long id = (long)entityManager.createQuery("select account_id from Account where username = :name").setParameter("name", name).getSingleResult();
		return (LocalTime)entityManager.createQuery("select daySumTime from Data where account_id = :id and state = :state and date = :date order by data_id desc").setParameter("id", id).setParameter("state", "退勤").setParameter("date", d).setMaxResults(1).getSingleResult(); 

	}
	public long findStartId(LocalDate d) {
		// TODO 自動生成されたメソッド・スタブ
		return (long)entityManager.createQuery("select data_id from Data where state = :state and date = :date").setParameter("date", d).setParameter("state", "出勤").getSingleResult();

	}
	public long findEndId(LocalDate d) {
		// TODO 自動生成されたメソッド・スタブ
		return (long)entityManager.createQuery("select data_id from Data where state = :state and date = :date").setParameter("date", d).setParameter("state", "退勤").getSingleResult();

	}
	public String findBool(String name, LocalDate d) {
		// TODO 自動生成されたメソッド・スタブ
		long id = (long)entityManager.createQuery("select account_id from Account where username = :name").setParameter("name", name).getSingleResult();
		return (String)entityManager.createQuery("select state from Data where account_id = :id and state = :state and date = :date").setParameter("id", id).setParameter("state", "有給").setParameter("date", d).setMaxResults(1).getSingleResult(); 


	}

}


