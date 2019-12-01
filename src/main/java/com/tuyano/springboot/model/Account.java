package com.tuyano.springboot.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="account")
public class Account{
	@OneToMany(mappedBy = "account")
	private List<Management> managers;
	
	public void setManagements(List<Management> managers) {
		this.managers = managers;
	}
	public List<Management> getManagements(){
		return managers;
	}
	@OneToMany(mappedBy = "account")
	private List<Data> suicas;
	
	public void setSuicas(List<Data> suicas) {
		this.suicas = suicas;
	}
	public List<Data> getSuicas(){
		return suicas;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private long account_id;
	@Column
	private String idm;
	@Column
	private String username;
	@Column
	private String password;
	@Column
	private String role;
	public void setId(long id) {
		this.account_id = account_id;
	}
	public long getId() {
		return account_id;
	}
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
	public void setRole(String role) {
		this.role = role;
	}
	public String getRole() {
		return role;
	}
	
	
}
