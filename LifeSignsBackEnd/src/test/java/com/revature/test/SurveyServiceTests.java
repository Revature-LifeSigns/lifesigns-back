package com.revature.test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.revature.model.CovidSurvey;
import com.revature.repository.CovidSurveyRepository;
import com.revature.service.CovidSurveyService;

public class SurveyServiceTests {

	@Mock
	private CovidSurveyService cServ;
	private CovidSurvey survey;
	private CovidSurveyRepository cRepo;
	private List<CovidSurvey> cList;
	
	@BeforeEach
	public void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
		cServ = mock(CovidSurveyService.class);
		survey = new CovidSurvey(1, 1, true, true, true);
		List<CovidSurvey> cList = new ArrayList<>();
		cList.add(survey);
	}
	
	@Test
	public void testInsertSurveySuccess() {
		cServ.insertSurvey(survey);
		verify(cServ).insertSurvey(survey);
	}
	
	@Test
	public void testDeleteSurveySuccess() {
		cServ.deleteSurvey(survey);
		verify(cServ).deleteSurvey(survey);
	}
	
	@Test
	public void testGetAllSurveysSuccess() {
		when(cServ.getAllSurveys()).thenReturn(cList);
		assertEquals(cServ.getAllSurveys(), cList);
	}

	@Test
	public void testGetSurveyBySurveyId() {
	when(cServ.getSurveyBySurveyId(1)).thenReturn(survey);
		assertEquals(cServ.getSurveyBySurveyId(1), survey);	
	}
	
	@Test
	public void testGetSurveysByUserId() {
		when(cServ.getSurveysByUserId(1)).thenReturn(cList);
		assertEquals(cServ.getSurveysByUserId(1), cList);	
	}
	
	
}
