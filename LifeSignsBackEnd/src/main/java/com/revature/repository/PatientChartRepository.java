package com.revature.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.model.PatientChart;

@Repository
public interface PatientChartRepository extends JpaRepository<PatientChart, String> {
	
	public List<PatientChart> findAll();
	public PatientChart findByChartid(int chartid);
	
}
