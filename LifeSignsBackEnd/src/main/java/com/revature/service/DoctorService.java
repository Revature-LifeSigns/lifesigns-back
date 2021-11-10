package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.model.Doctor;
import com.revature.repositories.DoctorRepository;

public class DoctorService {
	
	private DoctorRepository doctorRepo;
	
	public DoctorService() {
	}
	
	@Autowired
	public DoctorService(DoctorRepository doctorRepo) {
		super();
		this.doctorRepo = doctorRepo;
	}
	
	public List<Doctor> getAllDoctors(){
		return null;
	}
	
	public void insertDoctor(Doctor doctor) {
		doctorRepo.save(doctor);
	}
	
//	public Doctor getDoctorByID(int id) {
//		return doctorRepo.findByUserId(id);
//	}
//	
//	public Doctor getDoctorByUsername(String username) {
//		return doctorRepo.findByUserName(username);
//	}
	
	
	public String deleteDoctor(Doctor doctor) {
		// Can't write this out yet. Who is allowed to delete Doctors from the DB?
		return null;
	}
	
	public void updateDoctor(Doctor doctor) {
		doctorRepo.save(doctor);
	}
	
	public void updateDoctorCovidStatus() {
	}

}
