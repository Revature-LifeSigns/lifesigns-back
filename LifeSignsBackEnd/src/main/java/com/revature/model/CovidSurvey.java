package com.revature.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "covid_survey")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CovidSurvey {
	
	@Id
    @Column(name = "survey_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int surveyId;
	
    @Column(name = "user_id") 
	private int userId;
    
    @Column(name = "has_symptoms")
	private boolean hasSymptoms;
    
    @Column(name = "is_exposed")
	private boolean isExposed;
    
    @Column(name = "has_traveled")
	private boolean hasTraveled;

	public CovidSurvey(int userId, boolean hasSymptoms, boolean isExposed, boolean hasTraveled) {
		super();
		this.userId = userId;
		this.hasSymptoms = hasSymptoms;
		this.isExposed = isExposed;
		this.hasTraveled = hasTraveled;
	}
}
