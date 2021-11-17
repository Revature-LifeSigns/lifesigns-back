package com.revature.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.model.CovidSurvey;
import com.revature.model.User;

@Repository
public interface CovidSurveyRepository extends JpaRepository<CovidSurvey, Integer>{
	
	public List<CovidSurvey> findAll();
	public CovidSurvey findBySurveyId(int surveyId);
	public List<CovidSurvey> findByUserId(int userId);

}
