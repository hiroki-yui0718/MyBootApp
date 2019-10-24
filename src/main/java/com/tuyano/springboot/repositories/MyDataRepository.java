package com.tuyano.springboot.repositories;

import com.tuyano.springboot.MyData;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyDataRepository  extends JpaRepository<MyData, Long> {
	
	public Page<MyData> findAll(Pageable pageable);
}