package com.revature.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{
	
	public List<User> findAll();
	public User findByUsername(String username);
	public List<User> findByFirstName(String firstName);
	public List<User> findByLastName(String lastName);
	public User findByEmail(String email);
	public User findByUserid(int userid);
	
}