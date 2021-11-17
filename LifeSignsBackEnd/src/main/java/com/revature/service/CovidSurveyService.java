package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.CovidSurvey;
import com.revature.repository.CovidSurveyRepository;

@Service
public class CovidSurveyService {
	
	private CovidSurveyRepository csRepo;
	
	public CovidSurveyService() {
		
	}

	@Autowired
	public CovidSurveyService(CovidSurveyRepository csRepo) {
		super();
		this.csRepo = csRepo;
	}
	
	public void insertSurvey(CovidSurvey survey) {
		csRepo.save(survey);
	}
	
	public void deleteSurvey(CovidSurvey survey) {
		csRepo.delete(survey);
	}
	
	public List<CovidSurvey> getAllSurveys() {
		return csRepo.findAll();
	}
	
	public CovidSurvey getSurveyBySurveyId(int id) {
		return csRepo.findBySurveyId(id);
	}
	
	public List<CovidSurvey> getSurveysByUserId(int userId) {
		return csRepo.findByUserId(userId);
	}
}
