package com.revature.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.model.PatientChart;

public interface PatientChartRepository extends JpaRepository<PatientChart, String> {

}
