package com.revature.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "unit_lookup")
public class Unit {
	
	@Id
	@Column(name = "unitId")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int unitId;
	
	@Column(name = "unit")
	private String unit;
	
	public Unit() {
		// TODO Auto-generated constructor stub
	}

	public Unit(String unit) {
		super();
		this.unit = unit;
	}

	public int getId() {
		return unitId;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Override
	public String toString() {
		return "Unit [unitId=" + unitId + ", unit=" + unit + "]";
	}
}
