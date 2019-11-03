package com.tuyano.springboot.model;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="suica")
public class Suica {
	@Id
	@Column
	private String id;
	private ZonedDateTime date;
	private String state;
	private ZonedDateTime time;
	
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
	public void setDate(ZonedDateTime date) {
		this.date = date;
	}
	public ZonedDateTime getDate() {
		return date;
	}	
	public void setState(String state) {
		this.state = state;
	}
	public String getState() {
		return state;
	}
	public void setTime(ZonedDateTime time) {
		this.time = time;
	}
	public ZonedDateTime getTime() {
		return time;
	}	
	
}
