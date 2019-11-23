package com.tuyano.springboot.service;

import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
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
	@Transactional
	@Modifying
	public void RoleUpdate(long id, String role) {
		entityManager.createQuery("UPDATE Account set role = :role where id = :id").setParameter("role",role).setParameter("id",id).executeUpdate();
	}
	@Autowired
	private PasswordEncoder passwordEncoder;
	public enum ExampleRole {

		ROLE_EMPLOYEE,ROLE_ADMIN,ROLE_MANAGER
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 本来ならDBアクセスしてパスワードを取得するところだが、サンプルなのでプログラム直書き
		Account account = findOne(username);
		if(account == null) {
			throw new UsernameNotFoundException(username);
		}else {
		Set<ExampleRole> roles = null;
		String role = account.getRole();
		if(role.equals("ADMIN")) {
			roles = EnumSet.of(ExampleRole.ROLE_ADMIN);
			System.out.println(1);
		}else if(role.equals("MANAGER")){
			roles = EnumSet.of(ExampleRole.ROLE_MANAGER);
			System.out.println(2);
		}else if(role.equals("EMPLOYEE")){
			roles = EnumSet.of(ExampleRole.ROLE_EMPLOYEE);
			System.out.println(3);
		}
		Collection<? extends GrantedAuthority> authorities = roles.stream()
					.map(ExampleRole::name).map(SimpleGrantedAuthority::new)
					.collect(Collectors.toList());
		return new User(username, account.getPassword(),authorities);
		}
		
	}
}
