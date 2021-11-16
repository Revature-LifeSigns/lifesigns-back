package com.revature.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="unit_lookup")
public class Unit {

	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="unit")
	private String unit;
	
	
	
	public Unit(String unit) {
		super();
		this.unit = unit;
	}

	@Override
	public String toString() {
		return "Unit [id=" + id + ", unit=" + unit + "]";
	}
	
	
}
