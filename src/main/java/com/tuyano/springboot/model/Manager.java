package com.tuyano.springboot.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="manager")
public class Manager {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private long id;
	@Column
	private String idm;
	@Column
	private LocalDate date;
	@Column
	private String scheStartTime;
	@Column
	private String scheEndTime;
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
}
