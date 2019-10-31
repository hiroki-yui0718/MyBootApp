package com.tuyano.springboot.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="data")
public class Data {
	
	private String line;
	public String getLine() {
		return line;
	}
	public void setLine(String line) {
		this.line = line;
	}
}
