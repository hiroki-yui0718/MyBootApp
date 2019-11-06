package com.tuyano.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "sumtime")
public class SumTime {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	@Column
	private long id;
	@Column
	private String idm;
	@Column
	private int year;
	@Column
	private int month;
	@Column
	private long sumTime;
	
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
	public void setYear(int year) {
		this.year = year;
	}
	public int getYear() {
		return year;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getMonth() {
		return month;
	}
	public void setSumTime(long sumTime) {
		this.sumTime = sumTime;
	}
	public long getSumTime() {
		return sumTime;
	}
}
