package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.model.PatientChart;
import com.revature.repository.PatientChartRepository;

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
	
	public List<PatientChart> getAllCharts(){
		return pcRepo.findAll();
	}
    public void insertChart(PatientChart chart) {
    	
    	pcRepo.save(chart);
    }
	public void deleteChart(PatientChart chart) {
		pcRepo.delete(chart);
	}
	
	public PatientChart getChartByChartId(int chartid) {
		return pcRepo.findByChartid(chartid);
	}
	
	public void updateChart(PatientChart chart, PatientChart changes) {
		if (changes.getDoctor() != null) {
			chart.setDoctor(changes.getDoctor());
		}
		if (changes.getNurse() != null) {
			chart.setNurse(changes.getNurse());
		}
		if (changes.getFirstName() != null && !changes.getFirstName().isEmpty()) {
			chart.setFirstName(changes.getFirstName());
		}
		if (changes.getLastName() != null && !changes.getLastName().isEmpty()) {
			chart.setLastName(changes.getLastName());
		}
		if (changes.getEmail() != null && !changes.getEmail().isEmpty()) {
			chart.setEmail(changes.getEmail());
		}
		if (changes.getDob() != null) {
			chart.setDob(changes.getDob());
		}
		if (changes.getAddress() != null && !changes.getAddress().equals(";,")) {
			chart.setAddress(changes.getAddress());
		}
		if (changes.getInsuranceid() != null && !changes.getInsuranceid().isEmpty()) {
			chart.setInsuranceid(changes.getInsuranceid());
		}
		if (changes.getNotes() != null && !changes.getNotes().isEmpty()) {
			chart.setNotes(changes.getNotes());
		}
		if (changes.getDiagnosis() != null && !changes.getDiagnosis().isEmpty()) {
			chart.setDiagnosis(changes.getDiagnosis());
		}
		if (changes.getDiagnosis_approved() != null) {
			chart.setDiagnosis_approved(changes.getDiagnosis_approved());
		}
		if (changes.getTreatment() != null && !changes.getTreatment().isEmpty()) {
			chart.setTreatment(changes.getTreatment());
		}
	}

}
