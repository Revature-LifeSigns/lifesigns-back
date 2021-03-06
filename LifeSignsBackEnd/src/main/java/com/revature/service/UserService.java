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

	@Autowired
	public UserService(UserRepository uRepo) {
		super();
		this.uRepo = uRepo;
	}

	public List<User> getAllUsers() {
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
		return uRepo.findByFirstNameAndLastName(firstName, lastName);
	}

	public User getUserByUserId(int userid) {
		return uRepo.findByUserid(userid);

	}
	
	public void updateUser(User user, User changes) {
		if (changes.getRole() != null && !changes.getRole().isEmpty()) {
			user.setRole(changes.getRole());
		}
		if (changes.getUsername() != null && !changes.getUsername().isEmpty()) {
			user.setUsername(changes.getUsername());
		}
		if (changes.getPassword() != null && !changes.getPassword().isEmpty()) {
			user.setPassword(changes.getPassword());
		}
		if (changes.getEmail() != null && !changes.getEmail().isEmpty()) {
			user.setEmail(changes.getEmail());
		}
		if (changes.getFirstName() != null && !changes.getFirstName().isEmpty()) {
			user.setFirstName(changes.getFirstName());
		}
		if (changes.getLastName() != null && !changes.getLastName().isEmpty()) {
			user.setLastName(changes.getLastName());
		}
		if (changes.getDob() != null) {
			user.setDob(changes.getDob());
		}
		if (changes.getAddress() != null && !changes.getAddress().isEmpty()) {
			user.setAddress(changes.getAddress());
		}
		if (changes.getProfile_image() != null) {
			user.setProfile_image(changes.getProfile_image());
		}
		if (changes.getSpecialty() != null && !changes.getSpecialty().isEmpty()) {
			user.setSpecialty(changes.getSpecialty());
		}
		if (changes.getAboutMe() != null && !changes.getAboutMe().isEmpty()) {
			user.setAboutMe(changes.getAboutMe());
		}
		user.setViewPreference(changes.isViewPreference());
		if (changes.getCovidStatus() != null && !changes.getCovidStatus().isEmpty()) {
			user.setCovidStatus(changes.getCovidStatus());
		}
		
		uRepo.save(user);
	}

//	public User getUserByDob(LocalDate dob) {
//		return uRepo.findByDob(dob);
//	}
//
//	public User getUserByAddress(String address) {
//		return uRepo.findByAddress(address);
//	}
//
//	public User getUserByCovidStatus(String covid_status) {
//		return uRepo.findByCovidStatus(covid_status);
//	}
}
