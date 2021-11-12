package com.revature.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	public List<User> findAll();
	public User findByUsername(String username);
	public User findByPassword(String password);
	public User findByUsernameAndPassword(String username, String password);
	public User findByFirstName(String firstName);
	public User findByLastName(String lastName);
	public User findByEmail(String email);
	
}