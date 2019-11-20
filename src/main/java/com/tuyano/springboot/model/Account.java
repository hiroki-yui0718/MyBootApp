package com.tuyano.springboot.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="account")
public class Account {
	
	@OneToMany(mappedBy = "account")
	private List<Suica> suicas;
	
	public void setSuicas(List<Suica> suicas) {
		this.suicas = suicas;
	}
	public List<Suica> getSuicas(){
		return suicas;
	}
	@Column
	private String idm;
	@Id
	@Column
	private String username;
	@Column
	private String password;
	@Column
	private boolean role;
	public void setIdm(String idm) {
		this.idm = idm;
	}
	public String getIdm() {
		return idm;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsername() {
		return username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
	public void setRole(boolean role) {
		this.role = role;
	}
	public boolean getRole() {
		return role;
	}
	
	
}
