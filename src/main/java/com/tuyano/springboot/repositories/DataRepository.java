package com.tuyano.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tuyano.springboot.model.Data;

public interface DataRepository extends JpaRepository<Data, Long> {

}