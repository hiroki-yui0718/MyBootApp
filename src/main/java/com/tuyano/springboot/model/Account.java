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
	
	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
	private List<Suica> suicas;
	
	public void setSuicas(List<Suica> suicas) {
		this.suicas = suicas;
	}
	public List<Suica> getSuicas(){
		return suicas;
	}
	@Id
	@Column
	private String username;
	@Column
	private String idm;
	@Column
	private String password;
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
	
}
