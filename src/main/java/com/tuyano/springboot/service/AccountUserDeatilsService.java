package com.tuyano.springboot.service;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tuyano.springboot.model.Account;
import com.tuyano.springboot.model.MyData;
import com.tuyano.springboot.repositories.UserRepository;

@Service
public class AccountUserDeatilsService  implements UserDetailsService{
	@Autowired
	UserRepository userRepository;
	
	EntityManager entityManager;
	
	public Account findOne(String username,String password) {
		return (Account)entityManager.createQuery("from User where username" + username + "and passowrd" + password).getSingleResult();
	}
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username,String password)throws UsernameNotFoundException {
		Account account = Optional.ofNullable(findOne(username,password))
				.orElseThrow(() -> new UsernameNotFoundException("user not found."));
		return new AccountUserDetails(account,getAuthorities(account));
	}
	private Collection<GrantedAuthority> getAuthorities(Account account){
		return AuthorityUtils.createAuthorityList("ROLE_USER");
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
}
