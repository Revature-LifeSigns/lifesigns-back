package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.model.Unit;
import com.revature.model.UnitAssignment;
import com.revature.model.User;
import com.revature.repository.UnitAssignmentRepository;

@Service("unitAssignmentService")
@Transactional
public class UnitAssignmentService {
	private UnitAssignmentRepository uaRepo;

	public UnitAssignmentService() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	public UnitAssignmentService(UnitAssignmentRepository uaRepo) {
		super();
		this.uaRepo = uaRepo;
	}
	
	public List<UnitAssignment> getAllAssignments(){
		List<UnitAssignment> assignments = uaRepo.findAll();
		if(assignments.size() == 0) return null;
		return assignments;
	}
	
	public UnitAssignment getAssignedByUser(User user) {
		UnitAssignment assignment = uaRepo.findByUser(user);
		if(assignment == null) return null;
		return assignment;
	}
	
	public void insertUnitAssignment(UnitAssignment ua) {
		uaRepo.save(ua);
	}
	
	public void updateUnitAssignment(UnitAssignment ua) {
		uaRepo.updateAssignment(ua.getUser(), ua.getUnit());
	}
}
