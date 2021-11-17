package com.revature.repository;

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
	public User findByFirstAndLastname(String firstName, String lastName);

	public User findByUserid(int userid);
	public User findByUsernameAndPassword(String username, String password);


	
}