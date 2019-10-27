package com.tuyano.springboot.model;

import javax.persistence.*;

@Entity
@Table(name="user")
public class User {
	@Id
	private String userId;
	private String password;
	@Enumerated(EnumType.STRING)
	private RoleName roleName;
	public void setUserId(String userId) {  
		this.userId = userId;
	}
	public String getUserId() {
		return userId;
	}
	public void setPassword() {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
	public void setRoleName() {
		this.roleName = roleName;
	}
	public RoleName getRoleName() {
		return roleName;
	}
	
}
