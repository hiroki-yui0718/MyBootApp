package com.tuyano.springboot.model;
import java.time.LocalDate;
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
@Table(name="data")
public class Data {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private long data_id;
	@Column
	private String idm;
	@Column
	private LocalDate date;
	@Column
	private LocalTime time;
	@Column
	private String state;
	@Column
	private LocalTime daySumTime;
	@ManyToOne
	@JoinColumn(name="username")
	private Account account;
	
	public Data() {
		super();
		account =new Account();
	}

	public void setDataId(long data_id) {
		this.data_id = data_id;
	}
	public long getDataId() {
		return data_id;
	}
	public void setIdm(String idm) {
		this.idm = idm;
	}
	public String getIdm() {
		return idm;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public LocalDate getDate() {
		return date;
	}	
	public void setTime(LocalTime time) {
		this.time = time;
	}
	public LocalTime getTime() {
		return time;
	}	
	public void setState(String state) {
		this.state = state;
	}
	public String getState() {
		return state;
	}

	public void setDaySumTime(LocalTime daySumTime) {
		this.daySumTime = daySumTime;
	}
	public LocalTime getDaySumTime() {
		return daySumTime;
	}
	public Account getAccount(){
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
}
