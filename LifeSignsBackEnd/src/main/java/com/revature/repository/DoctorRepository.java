package com.revature.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.model.Doctor;

@Repository
public interface DoctorRepository extends CrudRepository<Doctor, String>{
	public List<Doctor> findAll();
//	public Doctor findByUsername(String userName);
	public Doctor findByUserId(int id);

}