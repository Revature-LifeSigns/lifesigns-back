package com.example.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.User;
import com.example.repository.UserRepository;

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
	
	public User getUserByEmail(String email){
		return uRepo.findByEmail(email);
	}
	public User getUserByRoleId(int RoleId){
		return uRepo.findByRoleid(RoleId);
}
	
	
	
	
}

