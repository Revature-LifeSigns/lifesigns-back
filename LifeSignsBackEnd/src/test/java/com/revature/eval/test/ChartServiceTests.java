package com.revature.eval.test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.revature.model.PatientChart;
import com.revature.model.User;
import com.revature.service.PatientChartService;
import com.revature.repository.UserRepository;
import com.revature.repository.PatientChartRepository;

public class ChartServiceTests {
	
	@MockBean
	private PatientChartService pcServ;
	private PatientChartRepository pcRepo;
	private UserRepository uRepo;
	private User user;
	private PatientChart chart;
	List<PatientChart> chartList = new ArrayList<>();
	
	@BeforeEach
    public void setUp() throws Exception {
		
		MockitoAnnotations.initMocks(this);
		
		pcServ = mock(PatientChartService.class);
		
		//Building Test PatientChart Java Object
//		chart.setChartid(1);
//		chart.setDoctor(user);
//		chart.setNurse(user);
//		chart.setFirstName("testName");
//		chart.setLastName("testName");
//		chart.setEmail("test@email.com");
//		chart.setDob(LocalDate.parse("1967-10-07"));
//		chart.setAddress("test address");
//		chart.setInsuranceid("0000");
//		chart.setNotes("test notes");
//		chart.setDiagnosis("test diagnosis");
//		chart.setDiagnosis_approved(true);
//		chart.setTreatment("test treatment");
//		
//		//adding chart to test patient charts list
//		chartList.add(chart);
//		
//		when(pcServ.getAllCharts()).thenReturn(chartList);
//		when(pcServ.getChartByChartId(1)).thenReturn(chart);
//    }
//	
//	@Test
//	public void testGetAllChartsSuccess() {
//		assertEquals(pcServ.getAllCharts(), chartList);
//	}
//	
//	@Test
//	public void testGetChartByIdSuccess() {
//		assertEquals(pcServ.getChartByChartId(1), chart);
//	}
//	
//	@Test
//	public void testInsertChartSuccess() {
//		pcServ.insertChart(chart);
//		verify(pcServ).insertChart(chart);
//	}
//	
//	@Test
//	public void testDeleteChartSuccess() {
//		pcServ.deleteChart(chart);
//		verify(pcServ).deleteChart(chart);
	}

}
