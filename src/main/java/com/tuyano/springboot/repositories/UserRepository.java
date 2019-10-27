package com.tuyano.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tuyano.springboot.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
