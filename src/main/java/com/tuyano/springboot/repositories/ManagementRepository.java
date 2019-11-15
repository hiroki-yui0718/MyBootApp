package com.tuyano.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tuyano.springboot.model.Management;

public interface ManagementRepository extends JpaRepository<Management, Long> {

}
