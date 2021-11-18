package com.revature.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.model.Unit;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Integer> {
	public List<Unit> findAll();
	public Unit findByUnit(String unit);
}
