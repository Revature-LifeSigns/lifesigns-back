package com.revature.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="unit_assignment")
public class UnitAssignment {
	
	@Id
	@Column(name = "assignmentId")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int unitAssignmentId;
	
	@OneToOne
    @JoinColumn(name="assigned_user", nullable=false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name="assigned_unit", nullable=false)
	private Unit unit;

	public UnitAssignment() {
		// TODO Auto-generated constructor stub
	}

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

	public int getUnitAssignmentId() {
		return unitAssignmentId;
	}

	@Override
	public String toString() {
		return "UnitAssignment [unitAssignmentId=" + unitAssignmentId + ", user=" + user + ", unit=" + unit + "]";
	}
	
}
