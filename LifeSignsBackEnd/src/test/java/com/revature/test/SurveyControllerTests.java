package com.revature.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.WebApplicationContext;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.revature.controller.FrontController;
import com.revature.model.CovidSurvey;
import com.revature.model.PatientChart;
import com.revature.model.User;
import com.revature.repository.CovidSurveyRepository;
import com.revature.service.CovidSurveyService;
import com.revature.service.PatientChartService;
import com.revature.service.UserService;
import com.revature.util.BcryptPasswordEncoder;

@SpringBootTest
public class SurveyControllerTests {

	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext context;
	
	@MockBean
	private CovidSurveyService cServ;
	
	@Mock
	private CovidSurvey survey;
	private CovidSurveyRepository cRepo;
	private List<CovidSurvey> cList;
	private Map<String, String> expectedInputJSON = new HashMap<>();
	private User user;
	
	@BeforeEach
	public void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
		cServ = mock(CovidSurveyService.class);
		User user = new User();
		user.setUserid(1);
		this.mockMvc = webAppContextSetup(context).build();
		
		expectedInputJSON.put("surveyId", "1");
		expectedInputJSON.put("userId", "1");
		expectedInputJSON.put("hasSymptoms", "true");
		expectedInputJSON.put("isExposed", "true");
		expectedInputJSON.put("hasTraveled", "true");
		
		
		survey = new CovidSurvey(1, 1, true, true, true);
		List<CovidSurvey> cList = new ArrayList<>();
		cList.add(survey);
	}
	
	@Test
	public void testGetAllSurveys() throws Exception {
	when(cServ.getAllSurveys()).thenReturn(cList);
	this.mockMvc.perform(MockMvcRequestBuilders.get("/LifeSigns/survey")
			.content(asJSONString(expectedInputJSON))
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
	}
	
	@Test
	public void testGetSurveysByUserId() throws Exception{
	when(cServ.getSurveysByUserId(1)).thenReturn(cList);
	when(user.getUserid()).thenReturn(1);
	this.mockMvc.perform(MockMvcRequestBuilders.get("/LifeSigns/survey/userid/" + user.getUserid())
			.content(asJSONString(expectedInputJSON))
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
		
		
		
	}
	
	@Test
	public void testGetSurveyBySurveyId() throws Exception{
		when(cServ.getSurveyBySurveyId(1)).thenReturn(survey);
		when(survey.getSurveyId()).thenReturn(1);
		this.mockMvc.perform(MockMvcRequestBuilders.get("/LifeSigns/survey/id/" +survey.getSurveyId())
				.content(asJSONString(expectedInputJSON))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void testInsertSurvey() throws Exception{
		doNothing().when(cServ).insertSurvey(survey);
		when(cServ.getSurveyBySurveyId(1)).thenReturn(null);
		this.mockMvc.perform(MockMvcRequestBuilders.post("/LifeSigns/survey/insert")
				.content(asJSONString(expectedInputJSON))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isAccepted());
	}
	
	@Test 
	public void testDeleteSurveyById() throws Exception{
		doNothing().when(cServ).deleteSurvey(cServ.getSurveyBySurveyId(1));
		when(cServ.getSurveyBySurveyId(1)).thenReturn(survey);
		when(survey.getSurveyId()).thenReturn(1);
		this.mockMvc.perform(MockMvcRequestBuilders.delete("/LifeSigns/survey/id/" + survey.getSurveyId())
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isAccepted());
	}
	
	public static String asJSONString(final Object obj) {
	    try {
	      return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	      throw new RuntimeException(e);
	    }
	}
	
}
