package com.tuyano.springboot.service;

import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
	public enum ExampleRole {

		ROLE_USER,ROLE_ADMIN
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 本来ならDBアクセスしてパスワードを取得するところだが、サンプルなのでプログラム直書き
		Account account = findOne(username);
		if(account == null) {
			throw new UsernameNotFoundException(username);
		}else {
		Set<ExampleRole> roles;
		System.out.println(account.getRole());
		if(account.getRole()) {
			roles = EnumSet.of(ExampleRole.ROLE_ADMIN);
		}else {
			roles = EnumSet.of(ExampleRole.ROLE_USER);
		}
		Collection<? extends GrantedAuthority> authorities = roles.stream()
					.map(ExampleRole::name).map(SimpleGrantedAuthority::new)
					.collect(Collectors.toList());
		return new User(username, account.getPassword(),authorities);
		}
	}
}
