package com.revature.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="unit_lookup")
public class UnitAssignment {
	
	@Id
	@Column(name="assigned_user")
	private int userId;
	
	@Column(name="assigned_unit")
	private int unitId;

	public UnitAssignment(int userId, int unitId) {
		super();
		this.userId = userId;
		this.unitId = unitId;
	}

	@Override
	public String toString() {
		return "UnitAssignment [userId=" + userId + ", unitId=" + unitId + "]";
	}
	
	
}
