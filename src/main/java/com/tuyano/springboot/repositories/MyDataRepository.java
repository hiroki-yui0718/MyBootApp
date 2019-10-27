package com.tuyano.springboot.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tuyano.springboot.model.MyData;

@Repository
public interface MyDataRepository  extends JpaRepository<MyData, Long> {
}