package com.tuyano.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tuyano.springboot.model.SumTime;

public interface SumTimeRepository extends JpaRepository<SumTime, String> {

}
