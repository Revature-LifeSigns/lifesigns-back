package com.revature.service;

import java.time.LocalDate;
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
	
	public User getUserByEmail(String email){
		return uRepo.findByEmail(email);
	}
	
	public User getUserByUserId(int userid) {
		return uRepo.findByUserid(userid);
	}
	
	public User getUserByDof(LocalDate dob) {
		return uRepo.findByDob(dob);
	}
	
	public User getUserByAddress(String address) {
		return uRepo.findByAddress(address);
	}	

	public User getUserByCovidStatus(String covid_status) {
		return uRepo.findByCovidStatus(covid_status);
}
}
