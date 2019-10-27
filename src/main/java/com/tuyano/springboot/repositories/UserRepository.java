package com.tuyano.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tuyano.springboot.model.Account;

@Repository
public interface UserRepository extends JpaRepository<Account, String> {

}
