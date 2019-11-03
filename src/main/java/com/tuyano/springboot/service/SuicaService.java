package com.tuyano.springboot.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.tuyano.springboot.model.Suica;

public class SuicaService {
	@PersistenceContext
	EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<Suica> findId() {
		return (List<Suica>)entityManager.createQuery("from Suica").getResultList();
	}
}
