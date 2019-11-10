package com.tuyano.springboot.model;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="suica")
public class Suica {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private long id;
	@Column
	private String idm;
	@Column
	private LocalDateTime date;
	@Column
	private String state;
	@Column
	private LocalTime dayTime;
	@Column
	private LocalTime monthTime;

	@ManyToOne
	private Account account;
	
	public Suica() {
		super();
		account =new Account();
	}

	public void setId(long id) {
		this.id = id;
	}
	public long getId() {
		return id;
	}
	public void setIdm(String idm) {
		this.idm = idm;
	}
	public String getIdm() {
		return idm;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public LocalDateTime getDate() {
		return date;
	}	
	public void setState(String state) {
		this.state = state;
	}
	public String getState() {
		return state;
	}
	public void setDayTime(LocalTime dayTime) {
		this.dayTime = dayTime;
	}
	public LocalTime getDayTime() {
		return dayTime;
	}
	public void setMonthTime(LocalTime monthTime) {
		this.monthTime = monthTime;
	}
	public LocalTime getMonthTime() {
		return monthTime;
	}
	public Account getAccount(){
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
}
