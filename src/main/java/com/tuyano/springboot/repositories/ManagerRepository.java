package com.tuyano.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tuyano.springboot.model.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Long> {

}
