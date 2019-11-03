package com.tuyano.springboot.service;

import java.time.ZonedDateTime;
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
		return (List<Suica>)entityManager.createQuery("from Suica").getResultList();
	}
	public Suica find(String idm) {
		return (Suica)entityManager.createQuery("from Suica where idm = :idm desc limit 1").setParameter("idm", idm).getSingleResult();
	}
}
