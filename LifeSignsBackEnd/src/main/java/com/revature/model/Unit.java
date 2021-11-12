package com.revature.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "unit_lookup")
public class Unit {
	
	@Id
	@Column(name = "id")
	private int id;
	
	@Column(name = "unit")
	private String unit;

	public Unit(String unit) {
		super();
		this.unit = unit;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Override
	public String toString() {
		return "Unit [id=" + id + ", unit=" + unit + "]";
	}
}
