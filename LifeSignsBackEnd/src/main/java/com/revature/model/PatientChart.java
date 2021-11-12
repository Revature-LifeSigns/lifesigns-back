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
    private int userid;
	
	@JoinColumn(name = "doctor")
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private User doctor;
	
	@JoinColumn(name = "nurse")
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
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
	
	
}
