package com.tuyano.springboot.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.tuyano.springboot.MyData;
import com.tuyano.springboot.model.User;
import com.tuyano.springboot.repositories.UserRepository;

@Service
public class ReservationUserDeatilsService  implements UserDetailsService{
	@Autowired
	UserRepository userRepository;
	@PersistenceContext
	EntityManager entityManager;
	public ReservationUserDeatilsService() {
		super();
	}
	public ReservationUserDeatilsService(EntityManager manager) {
		this();
		entityManager = manager;
	}
	
	public User findOne(String id) {
		return (User)entityManager.createQuery("from User where id" + id).getSingleResult();
	}
	public UserDetails loadUserByUsername(String username) {
		User user = findOne(username);
		return new ReservationUserDetails(user);
	}
}
