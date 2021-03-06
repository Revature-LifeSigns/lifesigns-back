package com.revature.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	public List<User> findAll();
	public User findByUsername(String username);
	public List<User> findByFirstName(String firstName);
	public List<User> findByLastName(String lastName);
	public User findByEmail(String email);
	public User findByRole(String role);
	public User findByFirstNameAndLastName(String firstName, String lastName);
	public User findByUserid(int userid);
	public User findByUsernameAndPassword(String username, String password);
	public User findByDob(LocalDate dob);
	public User findByAddress(String address);
	public User findByCovidStatus(String covid_status);
	
}