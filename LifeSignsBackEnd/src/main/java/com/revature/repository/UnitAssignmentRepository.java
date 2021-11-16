package com.revature.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.revature.model.Unit;
import com.revature.model.UnitAssignment;
import com.revature.model.User;

public interface UnitAssignmentRepository extends JpaRepository<UnitAssignment, Integer> {

	public List<UnitAssignment> findAll();
	public UnitAssignment findByUser(User user);
	@Modifying
	@Query("update UnitAssignment ua set ua.unit= ?2 where ua.user=?1")
	public void updateAssignment(User user, Unit newUA);

}
