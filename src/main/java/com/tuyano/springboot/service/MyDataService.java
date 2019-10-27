package com.tuyano.springboot.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tuyano.springboot.model.MyData;

@Service
public class MyDataService {
@PersistenceContext
private EntityManager entityManager;

@SuppressWarnings("unchekced")
public List<MyData> getAll(){
	return (List<MyData>)entityManager.createQuery("from MyData").getResultList();
}
public MyData get(int num) {
	return (MyData)entityManager.createQuery("from MyData where id" + num).getSingleResult();
}
public List<MyData> find(String fstr){
	CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	CriteriaQuery<MyData> query = builder.createQuery(MyData.class);
	Root<MyData> root = query.from(MyData.class);  //root,元
	
	query.select(root).where(builder.equal(root.get("name"),fstr));
	List<MyData> list = null;
	list = (List<MyData>)entityManager.createQuery(query).getResultList();
	return list;
	
}
}
