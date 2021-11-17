package com.revature.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "patientcharts")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientChart {
	
	@Id
    @Column(name = "chartid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int chartid;
	
	@JoinColumn(name = "doctor")
	@ManyToOne(fetch=FetchType.EAGER)
	private User doctor;
	
	@JoinColumn(name = "nurse")
	@ManyToOne(fetch=FetchType.EAGER)
	private User nurse;
	
	@Column(name="firstname", nullable=false)
	private String firstName;
	
	@Column(name="lastname", nullable=false)
	private String lastName;
	
    @Column(name = "email", unique = true, nullable = false)
    private String email;
	
	@Column(name="dob", nullable=false)
	private LocalDate dob;
	
	@Column(name="address")
	private String address;
	
	@Column(name="insuranceid")
	private String insuranceid;
	
	@Column(name="notes")
	private String notes;
	
	@Column(name="diagnosis")
	private String diagnosis; //initially created by nurse, doctor can change
	
	@Column(name="diagnosis_approved")
	private Boolean diagnosis_approved; //true if doctor approves diagnosis
	
	@Column(name="treatment")
	private String treatment; //created by doctor

	public PatientChart(User doctor, User nurse, String firstName, String lastName, String email, LocalDate dob,
			String address, String insuranceid, String notes, String diagnosis, Boolean diagnosis_approved,
			String treatment) {
		super();
		this.doctor = doctor;
		this.nurse = nurse;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.dob = dob;
		this.address = address;
		this.insuranceid = insuranceid;
		this.notes = notes;
		this.diagnosis = diagnosis;
		this.diagnosis_approved = diagnosis_approved;
		this.treatment = treatment;
	}
	
}
