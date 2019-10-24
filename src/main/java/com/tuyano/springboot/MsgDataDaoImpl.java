package com.tuyano.springboot;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

@SuppressWarnings("rawtypes")
@Repository
public class MsgDataDaoImpl implements MsgDataDao<MsgDataDao> {

	private EntityManager entityManager;
	
	public MsgDataDaoImpl(){
		super();
	}
	public MsgDataDaoImpl(EntityManager manager){
		entityManager = manager;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MsgData> getAll() {
		return entityManager
				.createQuery("from MsgData")
				.getResultList();
	}

	@Override
	public MsgData findById(long id) {
		return (MsgData)entityManager
				.createQuery("from MsgData where id = " 
				+ id).getSingleResult();
	}
	
	
}
