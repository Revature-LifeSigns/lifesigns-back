//package com.revature.service;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.revature.model.Nurse;
//import com.revature.repository.NurseRepository;
//
//@Service
//public class NurseService {
//	
//	private NurseRepository nRepo;
//	
//	
//	public NurseService() {
//		// TODO Auto-generated constructor stub
//	}
//
//
//	@Autowired
//	public NurseService(NurseRepository nRepo) {
//		super();
//		this.nRepo = nRepo;
//	}
//	
//	public List<Nurse>getAllNurse(){
//		return nRepo.findAll();
//	}
//	
//	public void insertNurse(Nurse nurse) {
//		nRepo.save(nurse);
//	}
//	
//	public Nurse getNurseByUsername(String userName) {
//		return nRepo.findByUserName(userName);
//	}
//	
//	
//	public Nurse getNurseByFirstnameAndLastName(String firstname, String lastname) {
//		return nRepo.findByFirstNameAndLastName(firstname, lastname);
//	}
//	
//	
//	
//	public Nurse getNurseByEmail(String email) {
//		return nRepo.findByEmail(email);
//	}
//	
//	
//	public Nurse getNurseById(int id) {
//		return nRepo.findByUserId(id);
//	}
//	
//	public void deleteNurse(Nurse nurse) {
//		nRepo.delete(nurse);
//		
//	}
//	
//	
//
//}
