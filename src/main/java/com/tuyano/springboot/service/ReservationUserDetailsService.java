package com.tuyano.springboot.service;

import java.util.Collections;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tuyano.springboot.model.Account;

@Service
public class ReservationUserDetailsService  implements UserDetailsService{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public Account findOne(String username) {
		return (Account)entityManager.createQuery("from Account where username = :username").setParameter("username", username).getSingleResult();
		//x一意を指定しないといけない
	}
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 本来ならDBアクセスしてパスワードを取得するところだが、サンプルなのでプログラム直書き
		Account account = findOne(username);
		if(account == null) {
			throw new UsernameNotFoundException(username);
		}else {
			

		return new User(username, this.passwordEncoder.encode(account.getPassword()), Collections.emptySet());
		}
	}
}
