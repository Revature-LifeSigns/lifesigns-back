package com.revature.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "users")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
    @Column(name = "userid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userid;

    @Column(name = "role")
    private String role; //doctor, nurse, admin

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "pwd", nullable = false)
    private String password;

    @Column(name = "email", unique = true, nullable = false)
    private String email;
    
	@Column(name="firstname", nullable=false)
	private String firstName;
	
	@Column(name="lastname", nullable=false)
	private String lastName;
	
	@Column(name="dob", nullable=false)
	private LocalDate dob;
	
	@Column(name="address")
	private String address;
	
    @Column(name="profile_image")
    private byte[] profile_image;
    
    @Column(name="specialty")
    private String specialty;
	
	@Column(name="about_me")
	private String aboutMe;
	
	@Column(name="view_preference")
	private boolean viewPreference;
	
	@Column(name="covid_status")
	private String covidStatus;

//	public User(String role, String username, String password, String email, String firstName, String lastName,
//			LocalDate dob, Boolean viewPreference) {
//		super();
//		this.role = role;
//		this.username = username;
//		this.password = password;
//		this.email = email;
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.dob = dob;
//		this.viewPreference = viewPreference;
//	}
	
    // Used to register a user
	public User(String role, String username, String password, String email, String firstname, String lastname,
			LocalDate dob, String address) {
		super();
		this.role = role;
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstName = firstname;
		this.lastName = lastname;
		this.dob = dob;
		this.address = address;
	}
	
	// Used to login a user
	public User(String role, String username, String password, String email) {
		super();
		this.role = role;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	// Used to edit account details
	public User(String role, String username, String password, String email, String address) {
		super();
		this.role = role;
		this.username = username;
		this.password = password;
		this.email = email;
		this.address = address;
	}
	
	// Used to edit profile
	public User(String username, byte[] profile_image, String specialty, String aboutMe, boolean viewPreference, String covid_status) {
		super();
		this.username = username;
		this.profile_image = profile_image;
		this.specialty = specialty;
		this.aboutMe = aboutMe;
		this.viewPreference = viewPreference;
		this.covidStatus = covid_status;
	}
}
