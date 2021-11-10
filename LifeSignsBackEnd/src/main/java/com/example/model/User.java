package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table
public class User {
	
@Id
@Column(name="user_id")
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int userID;

@Column(name="role_id")
private int roleID;

@Column(name="username")
private String username;

@Column(name="pwd")
private String password;

@Column(name="email")
private String email;

public User() {
	
}

public User(int userID, int roleID, String username, String password, String email) {
	super();
	this.userID = userID;
	this.roleID = roleID;
	this.username = username;
	this.password = password;
	this.email = email;
}

public User(String username, String password) {
	super();
	this.username = username;
	this.password = password;
}

public int getUserID() {
	return userID;
}

public void setUserID(int userID) {
	this.userID = userID;
}

public int getRoleID() {
	return roleID;
}

public void setRoleID(int roleID) {
	this.roleID = roleID;
}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

@Override
public String toString() {
	return "User [userID=" + userID + ", roleID=" + roleID + ", username=" + username + ", password=" + password
			+ ", email=" + email + "]";
}

}