package com.revature.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.model.User;
import com.revature.repository.UserRepository;


@Service("userService")
@Transactional
public class UserService {
	private UserRepository uRepo;
	
	public UserService() {
		
	}
	
	@Autowired
	public UserService(UserRepository uRepo) {
		super();
		this.uRepo = uRepo;
	}
	
	public List<User> getAllUsers(){
		return uRepo.findAll();
	}
	
    public void insertUser(User user) {
    	uRepo.save(user);
    }
    
	public void deleteUser(User user) {
		uRepo.delete(user);
	}
	
	
	public User getUserByUsername(String username){
		return uRepo.findByUsername(username);
	}
	
	public User getUserByPassword(String password){
		return uRepo.findByPassword(password);
	}
	
	
	public User getUserByUsernameAndPassword(String Username, String password) {
		return uRepo.findByUsernameAndPassword(Username, password);
		
	}
	
	
	public User getUserByRole(String role) {
		return uRepo.findByRole(role);
	}
	
	public User getUserByEmail(String email){
		return uRepo.findByEmail(email);
	}
	
	public User getUserByFirstAndLastname(String firstName, String lastName) {
		return uRepo.findByFirstAndLastname(firstName, lastName);
	}
	
}

