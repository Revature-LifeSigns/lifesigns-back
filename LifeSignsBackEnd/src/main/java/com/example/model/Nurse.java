package com.example.model;

import java.time.LocalDate;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Nurse")
public class Nurse {
	
	@Id
	@Column(name="user_id", unique=true, nullable=false )
	private int userId;
	
	@Column(name="firstname", nullable=false)
	private String firstName;
	
	@Column(name="lastname", nullable=false)
	private String lastName;
	
	@Column(name="username", unique=true, nullable=false)
	private String userName;
	
	@Column(name="email", nullable=false)
	private String email;
	
	@Column(name="dob", nullable=false)
	private LocalDate dob;
	
	@Column(name="profile_image")
	private byte[] profile_image;
	
	@Column(name="about_me")
	private String aboutMe;
	
	@Column(name="view_preference")
	private Boolean viewPreference;
	
	@Column(name="covid_status")
	private String covid_status;
	
	
	
	public Nurse() {
		// TODO Auto-generated constructor stub
	}



	public Nurse(String firstName, String lastName, String userName, String email, LocalDate dob, byte[] profile_image,
			String aboutMe, Boolean viewPreference, String covid_status) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.email = email;
		this.dob = dob;
		this.profile_image = profile_image;
		this.aboutMe = aboutMe;
		this.viewPreference = viewPreference;
		this.covid_status = covid_status;
	}



	public Nurse(int userId, String firstName, String lastName, String userName, String email, LocalDate dob,
			byte[] profile_image, String aboutMe, Boolean viewPreference, String covid_status) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.email = email;
		this.dob = dob;
		this.profile_image = profile_image;
		this.aboutMe = aboutMe;
		this.viewPreference = viewPreference;
		this.covid_status = covid_status;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public LocalDate getDob() {
		return dob;
	}



	public void setDob(LocalDate dob) {
		this.dob = dob;
	}



	public byte[] getProfile_image() {
		return profile_image;
	}



	public void setProfile_image(byte[] profile_image) {
		this.profile_image = profile_image;
	}



	public String getAboutMe() {
		return aboutMe;
	}



	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}



	public Boolean getViewPreference() {
		return viewPreference;
	}



	public void setViewPreference(Boolean viewPreference) {
		this.viewPreference = viewPreference;
	}



	public String getCovid_status() {
		return covid_status;
	}



	public void setCovid_status(String covid_status) {
		this.covid_status = covid_status;
	}



	public int getUserId() {
		return userId;
	}



	@Override
	public String toString() {
		return "Nurse [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", userName="
				+ userName + ", email=" + email + ", dob=" + dob + ", profile_image=" + Arrays.toString(profile_image)
				+ ", aboutMe=" + aboutMe + ", viewPreference=" + viewPreference + ", covid_status=" + covid_status
				+ "]";
	}
	
	
	
	



	
	
	
	


	
	
	
	
	
	
	
	

}
