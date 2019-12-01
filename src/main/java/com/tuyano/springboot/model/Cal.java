package com.tuyano.springboot.model;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Cal")
public class Cal {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private long cal_id;
	@Column
	private String idm;
	@Column
	private int year;
	@Column
	private String day;
	@Column
	private String week;
	@Column
	private LocalTime scheStartTime;
	@Column
	private LocalTime scheEndTime;
	@Column
	private LocalTime startTime;
	@Column
	private LocalTime endTime;
	@Column
	private LocalTime overTime;
	@Column
	private LocalTime daySumTime;
	
	public void setCalId(long cal_id) {
		this.cal_id = cal_id;
	}
	public long getCalId() {
		return cal_id;
	}
	public void setIdm(String idm) {
		this.idm = idm;
	}
	public String getIdm() {
		return idm;
	}
	public void setYear(int year) {
		// TODO 自動生成されたメソッド・スタブ
		this.year =year;
	}
	public int getYear() {
		// TODO 自動生成されたメソッド・スタブ
		return year;
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
	public void setScheStartTime(LocalTime scheStartTime) {
		this.scheStartTime = scheStartTime;
	}
	public LocalTime getScheStartTime() {
		return scheStartTime;
	}
	public LocalTime getScheEndTime() {
		return scheEndTime;
	}
	public void setScheEndTime(LocalTime shceEndTime) {
		this.scheEndTime = shceEndTime;
	}
	public LocalTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	public LocalTime getEndTime() {
		return endTime;
	}
	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}
	public LocalTime getOverTime() {
		return overTime;
	}
	public void setOverTime(LocalTime overTime) {
		this.overTime = overTime;
	}
	public LocalTime getDaySumTime() {
		return daySumTime;
	}
	public void setDaySumTime(LocalTime daySumTime) {
		this.daySumTime = daySumTime;
	}
}
