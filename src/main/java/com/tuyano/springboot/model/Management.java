package com.tuyano.springboot.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="management")
public class Management {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private long management_id;
	@Column
	private String name;
	@Column
	private LocalDate date;
	@Column
	private String monthSumTime;
	@Column
	private LocalTime scheStartTime;
	@Column
	private LocalTime scheEndTime;
	@Column
	private LocalDateTime createdTime;
	@ManyToOne
	@JoinColumn(name="account_id")
	private Account account;
	public Management() {
		super();
		account =new Account();
	}
	public void setManagementId(long management_id) {
		this.management_id = management_id;
	}
	public long getManagementId() {
		return management_id;
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

	public LocalTime getScheStringTime() {
		return scheStartTime;
	}
	public void setScheStratTime(LocalTime scheStartTime) {
		this.scheStartTime = scheStartTime;
	}
	public LocalTime getScheEndTime() {
		return scheEndTime;
	}
	public void setScheEndTime(LocalTime scheEndTime) {
		this.scheEndTime = scheEndTime;
	}
	public String getMonthSumTime() {
		return monthSumTime;
	}
	public void setMonthSumTime(String monthSumTime) {
		this.monthSumTime = monthSumTime;
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
