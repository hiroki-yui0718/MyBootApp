package com.tuyano.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="management")
public class Management {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="manage_id")
	private long manage_id;
	@Column
	private String idm;
	@Column
	private int year;
	@Column
	private String day;
	@Column
	private String week;
	@Column
	private String scheStartTime;
	@Column
	private String scheEndTime;
	@Column
	private String startTime;
	@Column
	private String endTime;
	@Column
	private String time;
	@Column
	private String sumTime;
	public void setManageId(long manage_id) {
		this.manage_id = manage_id;
	}
	public long getManageId() {
		return manage_id;
	}
	public void setIdm(String idm) {
		this.idm = idm;
	}
	public String getIdm() {
		return idm;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getDay() {
		return day;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public String getWeek() {
		return week;
	}
	public void setScheStartTime(String scheStartTime) {
		this.scheStartTime = scheStartTime;
	}
	public String getScheStartTime() {
		return scheStartTime;
	}
	public String getScheEndTime() {
		return scheEndTime;
	}
	public void setScheEndTime(String shceEndTime) {
		this.scheEndTime = shceEndTime;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getSumTime() {
		return sumTime;
	}
	public void setSumTime(String sumTime) {
		this.sumTime = sumTime;
	}
	public void setYear(int year) {
		// TODO 自動生成されたメソッド・スタブ
		this.year =year;
	}
	public int getYear() {
		// TODO 自動生成されたメソッド・スタブ
		return year;
	}
}
