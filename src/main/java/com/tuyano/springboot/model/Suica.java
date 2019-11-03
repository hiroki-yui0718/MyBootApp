package com.tuyano.springboot.model;

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
	
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
}
