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



}