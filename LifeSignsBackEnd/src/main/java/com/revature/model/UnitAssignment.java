package com.revature.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_assignment")
public class UnitAssignment {
	
	@Id
	@Column(name = "assigned_user")
	private User user;
	
	@Column(name = "assigned_unit")
	private Unit unit;

	public UnitAssignment(User user, Unit unit) {
		super();
		this.user = user;
		this.unit = unit;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	@Override
	public String toString() {
		return "UnitAssignment [user=" + user + ", unit=" + unit + "]";
	}
}
