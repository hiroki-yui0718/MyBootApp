package com.tuyano.springboot.model;

import javax.persistence.*;

@Entity
@Table(name="user")
public class Account {
	@Id
	private String username;
	private String password;
	public void setUserName(String username) {  
		this.username = username;
	}
	public String getUserName() {
		return username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
	public boolean isEnabled() {
		return true;
	}
	
}
