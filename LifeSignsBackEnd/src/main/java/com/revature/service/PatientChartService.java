package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.model.PatientChart;
import com.revature.repositories.PatientChartRepository;

@Service("patientChartService")
@Transactional
public class PatientChartService {
	private PatientChartRepository pcRepo;
	
	public PatientChartService() {
	}

	@Autowired
	public PatientChartService(PatientChartRepository pcRepo) {
		super();
		this.pcRepo = pcRepo;
	}
	
	public List<PatientChart> getAllUsers(){
		return pcRepo.findAll();
	}
    public void insertChart(PatientChart chart) {
    	pcRepo.save(chart);
    }
	public void deleteChart(PatientChart chart) {
		pcRepo.delete(chart);
	}
	

}
