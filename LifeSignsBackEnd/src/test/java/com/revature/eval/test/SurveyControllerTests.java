package com.revature.eval.test;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.revature.model.CovidSurvey;
import com.revature.model.User;
import com.revature.repository.CovidSurveyRepository;
import com.revature.service.CovidSurveyService;

@SpringBootTest
public class SurveyControllerTests {

	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext context;
	
	@MockBean
	private CovidSurveyService cServ;
	
	@Mock
	//private CovidSurvey survey;
	private CovidSurveyRepository cRepo;
	private List<CovidSurvey> cList;
	private Map<String, String> expectedInputJSON = new HashMap<>();
	private User user;
	
	@BeforeEach
	public void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
		cServ = mock(CovidSurveyService.class);
		user = mock(User.class);
		user.setUserid(1);
		this.mockMvc = webAppContextSetup(context).build();
		
		expectedInputJSON.put("surveyId", "1");
		expectedInputJSON.put("userId", "1");
		expectedInputJSON.put("hasSymptoms", "true");
		expectedInputJSON.put("isExposed", "true");
		expectedInputJSON.put("hasTraveled", "true");
		
		
		CovidSurvey survey = new CovidSurvey(1, 1, true, true, true);
		List<CovidSurvey> cList = new ArrayList<>();
		cList.add(survey);
	}
	
	@Test
	public void testGetAllSurveys() throws Exception {//works
	when(cServ.getAllSurveys()).thenReturn(cList);
	this.mockMvc.perform(MockMvcRequestBuilders.get("/LifeSigns/survey")
			.content(asJSONString(expectedInputJSON))
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
	}
	
	@Test
	public void testGetSurveysByUserId() throws Exception{//works
	when(cServ.getSurveysByUserId(1)).thenReturn(cList);
	//when(user.getUserid()).thenReturn(1);
	this.mockMvc.perform(MockMvcRequestBuilders.get("/LifeSigns/survey/userid/1")
			.content(asJSONString(expectedInputJSON))
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
	}
	
	@Test
	public void testGetSurveyBySurveyId() throws Exception{//fails
		CovidSurvey survey = new CovidSurvey(1, 1, true, true, true);
		
		when(cServ.getSurveyBySurveyId(1)).thenReturn(survey);
		//when(survey.getSurveyId()).thenReturn(1);
		//when(this.mockMvc.perform(MockMvcRequestBuilders.get("/LifeSigns/survey/id/1")).thenReturn(status().isOk());
		System.out.println(cServ.getSurveyBySurveyId(1));
		this.mockMvc.perform(MockMvcRequestBuilders.get("/LifeSigns/survey/id/1")
				.content(asJSONString(expectedInputJSON))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void testInsertSurvey() throws Exception{//works
		CovidSurvey survey = new CovidSurvey(1, 1, true, true, true);
		doNothing().when(cServ).insertSurvey(survey);
		when(cServ.getSurveyBySurveyId(1)).thenReturn(null);
		this.mockMvc.perform(MockMvcRequestBuilders.post("/LifeSigns/survey/insert")
				.content(asJSONString(expectedInputJSON))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isAccepted());
	}
	
	@Test 
	public void testDeleteSurveyByIdFailure() throws Exception{//works
		CovidSurvey survey = new CovidSurvey(1, 1, true, true, true);
		doNothing().when(cServ).deleteSurvey(survey);
		when(cServ.getSurveyBySurveyId(1)).thenReturn(survey);
		//when(survey.getSurveyId()).thenReturn(1);
		this.mockMvc.perform(MockMvcRequestBuilders.delete("/LifeSigns/survey/id/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isForbidden());
		
	}
	
	public static String asJSONString(final Object obj) {
	    try {
	      return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	      throw new RuntimeException(e);
	    }
	}
	
}