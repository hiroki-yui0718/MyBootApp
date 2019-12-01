package com.tuyano.springboot.service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

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
}
