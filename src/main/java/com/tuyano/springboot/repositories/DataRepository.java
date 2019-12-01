package com.tuyano.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tuyano.springboot.model.Data;

@Repository
public interface DataRepository extends JpaRepository<Data, Long> {

}