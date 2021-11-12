package com.revature.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.model.Nurse;



@Repository
public interface NurseRepository  extends JpaRepository<Nurse, Integer>{
	public List<Nurse> findAll();
	public Nurse findByUserName(String userName);
	public Nurse findByUserId(int userId);
	public Nurse findByFirstNameAndLastName(String firstName, String lastName);
	public Nurse findByEmail(String email);

}
