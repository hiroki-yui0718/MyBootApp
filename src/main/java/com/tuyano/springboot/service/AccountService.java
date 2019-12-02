package com.tuyano.springboot.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuyano.springboot.model.Account;

@Service
public class AccountService {
	@Autowired
	EntityManager entityManager;

	public Account findAccount(String idm) {
		// TODO 自動生成されたメソッド・スタブ
		return (Account)entityManager.createQuery("from Account where idm = :idm").setParameter("idm", idm).getSingleResult();
	}
	public String findName(String idm) {
		long id = (long)entityManager.createQuery("select account_id from Account where idm = :idm").setParameter("idm", idm).setMaxResults(1).getSingleResult();
		return (String)entityManager.createQuery("select username from Account where account_id = :id").setParameter("id", id).getSingleResult();
	}
	public long findId(String username) {
		return (long)entityManager.createQuery("select account_id from Account where username = :username").setParameter("username", username).getSingleResult();
	}
	@SuppressWarnings("unchecked")
	public List<Account> findDataAll(){
		return (List<Account>)entityManager.createQuery("from Account").getResultList(); 
	}
	public String findName(long id) {
		return (String)entityManager.createQuery("select username from Account where account_id = :id").setParameter("id",id).getSingleResult(); 
		
	}
	public Account findAll(String username) {
		return (Account)entityManager.createQuery("from Account where username = :username").setParameter("username", username).getSingleResult();
	}
	public String getIdm(String name) {
		return (String)entityManager.createQuery("select idm from Account where username = :name").setParameter("name", name).getSingleResult();
	}
}
