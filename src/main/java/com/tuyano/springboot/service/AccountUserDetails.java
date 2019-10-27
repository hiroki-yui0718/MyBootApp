package com.tuyano.springboot.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.tuyano.springboot.model.Account;

public class AccountUserDetails implements UserDetails {
	
	private final Account account;
	private final Collection<GrantedAuthority> authorities;
	
	public AccountUserDetails(Account account,Collection<GrantedAuthority> authorities) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.account = account;
		this.authorities = authorities;
	}
	public String getPassword() {
		return account.getPassword();
	}
	@Override
	public String getUsername() {
		return account.getUserName();
	}

	@Override
	public boolean isEnabled() {
		// TODO 自動生成されたメソッド・スタブ
		return account.isEnabled();
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO 自動生成されたメソッド・スタブ
		return authorities;
	}
	public Account getAccount() {
		return account;
	}
}
