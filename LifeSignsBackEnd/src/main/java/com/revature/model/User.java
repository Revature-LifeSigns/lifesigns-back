package com.revature.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "userid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userid;

    @Column(name = "roleid")
    private int roleid;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "pwd", nullable = false)
    private String password;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    public User() {

    }

    public User(int userid, int roleid, String username, String password, String email) {
        super();
        this.userid = userid;
        this.roleid = roleid;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(int roleid, String username, String password, String email) {
        super();
        this.roleid = roleid;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
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
        return "User [userid=" + userid + ", roleid=" + roleid + ", username=" + username + ", password=" + password +
            ", email=" + email + "]";
    }
}