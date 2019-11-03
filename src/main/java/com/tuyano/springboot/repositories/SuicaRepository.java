package com.tuyano.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tuyano.springboot.model.Suica;

public interface SuicaRepository extends JpaRepository<Suica, String> {

}