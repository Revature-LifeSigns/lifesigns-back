//package com.revature.model;
//
//import java.util.Date;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToOne;
//import javax.persistence.Table;
//import javax.sql.rowset.serial.SerialBlob;
//
//@Entity
//@Table(name="doctors")
//public class Doctor {
//
//	/**
//	 * 	User_id (references Users)
//		Firstname
//		Lastname
//		DOB 
//		Address
//		Picture (using S3) 
//		About Me 
//		*Specialty
//		View preference (boolean: 1-light, 0-dark) 
//		COVID status (none, exposed, vaccinated, quarantining) - lookup table 
//	 */
//	
//	@Column(name="Firstname", nullable=false)
//	private String Firstname;
//	
//	@Column(name="Lastname", nullable=false)
//	private String Lastname;
//	
//	@Column(name="DOB", nullable=false)
//	private Date DOB;
//	
//	@Column(name="address")
//	private String address;
//	
//	@Column(name="picture")
//	private SerialBlob picture;
//	
//	@Column(name="aboutMe")
//	private String aboutMe;
//	
////	@Column(name="specialty")
////	private String specialty;
//	
//	@Column(name="view_preference")
//	private boolean darkMode;
//	
//	@Column(name="COVID_status")
//	CovidStatus covidStatus;
//	
//	@Id
//	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
//	@JoinColumn(name="user_fk")
//	//remove the comment here and delete 'Object' when the user model is ready
//	private Object /*  User */ user;
//	
//	public Doctor() {
//		// TODO Auto-generated constructor stub
//	}
//
//	//
//	//CHANGE user DATATYPE TO User
//	//
//	//constructor with all fields
//	public Doctor(int userId, String firstname, String lastname, Date dOB, String address, SerialBlob picture,
//			String aboutMe, boolean darkMode, CovidStatus covidStatus, Object user) {
//		super();
//		//this.userId = userId;
//		Firstname = firstname;
//		Lastname = lastname;
//		DOB = dOB;
//		this.address = address;
//		this.picture = picture;
//		this.aboutMe = aboutMe;
//		this.darkMode = darkMode;
//		this.covidStatus = covidStatus;
//		this.user = user;
//	}
//
//	//
//	//CHANGE user DATATYPE TO User
//	//
//	//constructor with id, first name, last name, DOB, and user object
//	public Doctor(int userId, String firstname, String lastname, Date dOB, Object user) {
//		super();
//		//this.userId = userId;
//		Firstname = firstname;
//		Lastname = lastname;
//		DOB = dOB;
//		this.user = user;
//	}
//
////	public int getUserId() {
////		return userId;
////	}
////
////	public void setUserId(int userId) {
////		this.userId = userId;
////	}
//
//	public String getFirstname() {
//		return Firstname;
//	}
//
//	public void setFirstname(String firstname) {
//		Firstname = firstname;
//	}
//
//	public String getLastname() {
//		return Lastname;
//	}
//
//	public void setLastname(String lastname) {
//		Lastname = lastname;
//	}
//
//	public Date getDOB() {
//		return DOB;
//	}
//
//	public void setDOB(Date dOB) {
//		DOB = dOB;
//	}
//
//	public String getAddress() {
//		return address;
//	}
//
//	public void setAddress(String address) {
//		this.address = address;
//	}
//
//	public SerialBlob getPicture() {
//		return picture;
//	}
//
//	public void setPicture(SerialBlob picture) {
//		this.picture = picture;
//	}
//
//	public String getAboutMe() {
//		return aboutMe;
//	}
//
//	public void setAboutMe(String aboutMe) {
//		this.aboutMe = aboutMe;
//	}
//
////	public String getSpecialty() {
////		return specialty;
////	}
////
////	public void setSpecialty(String specialty) {
////		this.specialty = specialty;
////	}
//
//	public boolean isDarkMode() {
//		return darkMode;
//	}
//
//	public void setDarkMode(boolean darkMode) {
//		this.darkMode = darkMode;
//	}
//
//	public CovidStatus getCovidStatus() {
//		return covidStatus;
//	}
//
//	public void setCovidStatus(CovidStatus covidStatus) {
//		this.covidStatus = covidStatus;
//	}
//
//	//
//	//CHANGE user DATATYPE TO User
//	//
//	public Object getUser() {
//		return user;
//	}
//
//	//
//	//CHANGE user DATATYPE TO User
//	//
//	public void setUser(Object user) {
//		this.user = user;
//	}
//
//	//ToString with all variables
//	@Override
//	public String toString() {
//		return "Doctor [Firstname=" + Firstname + ", Lastname=" + Lastname + ", DOB=" + DOB
//				+ ", address=" + address + ", picture=" + picture + ", aboutMe=" + aboutMe
//				+ ", darkMode=" + darkMode + ", covidStatus=" + covidStatus + ", user=" + user + "]";
//	}
//	
//	
//	
//	
//}
