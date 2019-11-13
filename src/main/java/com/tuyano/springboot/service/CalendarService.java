package com.tuyano.springboot.service;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalendarService {
	@Autowired
	EntityManager entityManager;
	
}
