package com.example.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>  {
	public List<User> findAll();
	//public Optional<User> findById(int id);
	public User findByUsername(String username);
	public User findByPassword(String password);
	//public User findByUsernameAndPassword(String userame, String password);
	public User findByEmail(String email);
	public User findByRoleid(int roleID);

}
