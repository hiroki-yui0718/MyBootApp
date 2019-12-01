package com.tuyano.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tuyano.springboot.model.Cal;
@Repository
public interface CalRepository extends JpaRepository<Cal, Long> {

}
