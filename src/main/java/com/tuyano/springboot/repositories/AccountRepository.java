package com.tuyano.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tuyano.springboot.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
