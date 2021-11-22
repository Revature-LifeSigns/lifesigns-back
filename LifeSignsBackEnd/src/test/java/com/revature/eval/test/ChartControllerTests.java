
package com.revature.eval.test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.revature.model.PatientChart;
import com.revature.model.User;
import com.revature.service.PatientChartService;
import com.revature.service.UserService;
import com.revature.util.BcryptPasswordEncoder;

@SpringBootTest
public class ChartControllerTests {
	
	
	private MockMvc mockMvc;
	@Autowired 
	private BcryptPasswordEncoder passwordEncoder;
	@Autowired
	private WebApplicationContext context;
	
	
	@MockBean
	private UserService uServ;
	@MockBean
	private PatientChartService pcServ;
	@Mock
	private User databaseUser;
	private PatientChart chart;
	private Map<String, String> expectedInputJSON = new HashMap<>();
	private List<PatientChart> chartList;
	
	@BeforeEach
    public void setUp() throws Exception{
		this.mockMvc = webAppContextSetup(context).build();
		//PatientChartService pcServ = new PatientChartService();
	
		//Building Test PatientChart JSON
		//Map<String, String> expectedInputJSON = new HashMap<>();
		expectedInputJSON.put("User", "doctor");
		expectedInputJSON.put("User", "nurse");
		expectedInputJSON.put("firstName", "testName");
		expectedInputJSON.put("lastName", "testName");
		expectedInputJSON.put("email", "test@email.com");
		expectedInputJSON.put("dob", "1968/10/07");
		expectedInputJSON.put("address", "test address");
		expectedInputJSON.put("insuranceid", "0000");
		expectedInputJSON.put("notes", "test notes");
		expectedInputJSON.put("diagnosis", "test diagnosis");
		expectedInputJSON.put("diagnosis_approved", "true");
		expectedInputJSON.put("treatment", "test treatment");
		
		//Building Test PatientChart Java Object
		chart = mock(PatientChart.class);
		databaseUser = mock(User.class);
		chart.setChartid(1);
		chart.setDoctor(databaseUser);
		chart.setNurse(databaseUser);
		chart.setFirstName("testName");
		chart.setLastName("testName");
		chart.setEmail("test@email.com");
		chart.setDob(LocalDate.parse("1967-10-07"));
		chart.setAddress("test address");
		chart.setInsuranceid("0000");
		chart.setNotes("test notes");
		chart.setDiagnosis("test diagnosis");
		chart.setDiagnosis_approved(true);
		chart.setTreatment("test treatment");
		//adding chart to test list
		List<PatientChart> chartList= new ArrayList<>();
		chartList.add(chart);
    }
	
	@Test
	public void testGetChartSuccess() throws Exception {//works
		when(pcServ.getAllCharts()).thenReturn(chartList);
		this.mockMvc.perform(MockMvcRequestBuilders.get("/LifeSigns/chart")
				.content(asJSONString(expectedInputJSON))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void testGetChartByIdUnsuccess() throws Exception {//fails
		when(pcServ.getChartByChartId(chart.getChartid())).thenReturn(chart);
		//when(chart.getChartid()).thenReturn(1);
		this.mockMvc.perform(MockMvcRequestBuilders.get("/LifeSigns/chart/id/1")
				.content(asJSONString(expectedInputJSON))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}
	
	@Test
	public void testUpdateChartUnsuccess() throws Exception {//fails
		when(pcServ.getChartByChartId(1)).thenReturn(chart);
		doNothing().when(pcServ).insertChart(chart);
		this.mockMvc.perform(MockMvcRequestBuilders.patch("/LifeSigns/chart/update")
				.content(asJSONString(expectedInputJSON))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void testInsertChartUnsuccess() throws Exception {//fails
		doNothing().when(pcServ).insertChart(chart);
		when(pcServ.getChartByChartId(1)).thenReturn(chart);
		this.mockMvc.perform(MockMvcRequestBuilders.post("/LifeSigns/chart/insert")
				.content(asJSONString(expectedInputJSON))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void testDeleteChartSuccsess() throws Exception {//works
		when(pcServ.getChartByChartId(1)).thenReturn(chart);
		when(chart.getChartid()).thenReturn(1);
		doReturn(chart).when(pcServ).deleteChart(pcServ.getChartByChartId(1));
		this.mockMvc.perform(MockMvcRequestBuilders.delete("/LifeSigns/chart/id/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isAccepted());
	}
	
	public static String asJSONString(Object obj) {
	    try {
	      return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	      throw new RuntimeException(e);
	    }
	}
}