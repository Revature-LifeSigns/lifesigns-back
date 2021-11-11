package com.revature.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TreatmentRepository extends CrudRepository<Treatment, String>{
	public List<Treatment> findAll();
	public List<Treatment> findByDiagnosisId(int id);
	public List<Treatment> findByUserId(int id);
}
