package com.tuyano.springboot.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="manager")
public class Manager {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private long id;
	@Column
	private String name;
	@Column
	private LocalDate date;
	@Column
	private String scheStartTime;
	@Column
	private String scheEndTime;
	@Column
	private LocalDateTime createdTime;
	@ManyToOne
	@JoinColumn(name="username")
	private Account account;
	public Manager() {
		super();
		account =new Account();
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getId() {
		return id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public LocalDate getDate() {
		return date;
	}

	public String getScheStringTime() {
		return scheStartTime;
	}
	public void setScheStratTime(String scheStartTime) {
		this.scheStartTime = scheStartTime;
	}
	public String getScheEndTime() {
		return scheEndTime;
	}
	public void setScheEndTime(String scheEndTime) {
		this.scheEndTime = scheEndTime;
	}
	public LocalDateTime getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(LocalDateTime createdTime) {
		this.createdTime = createdTime;
	}
	public Account getAccoun(){
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	
}
