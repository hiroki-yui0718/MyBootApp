package com.tuyano.springboot.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.tuyano.springboot.model.User;

public class ReservationUserDetails implements UserDetails {
	
	private final User user;
	
	public ReservationUserDetails(User user) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.user = user;
	}
	public User getUser() {
		return user;
	}
	@Override
	public String getUsername() {
		return this.user.getUserId();
	}

	@Override
	public String getPassword() {
		// TODO 自動生成されたメソッド・スタブ
		return this.user.getPassword();
	}

	@Override
	public boolean isEnabled() {
		// TODO 自動生成されたメソッド・スタブ
		return true;
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
		return AuthorityUtils.createAuthorityList("ROLE_" + this.user.getRoleName().name());
	}
}
