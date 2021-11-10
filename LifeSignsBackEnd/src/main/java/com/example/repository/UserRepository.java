package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	public List<User> findAll();
	public User findByUserName(String uname);
	public List<User> findByUserRole(int roleID);
	public User findByUserNameAndEmail(String uname, String email);
	public User findByUserID(int userID);
	public User updateUser(User u);

}
