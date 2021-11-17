package com.revature.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.revature.model.User;
import com.revature.repository.UserRepository;
import com.revature.service.UserService;

@SpringBootTest
public class UserServiceTests {
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext context;
	
	@MockBean
	private UserRepository uRepo;
	
	private UserService uServ;
	private User databaseUser, changesUser;
	
	@BeforeEach
	public void setUp() throws Exception {
		this.mockMvc = webAppContextSetup(context).build();
		
		LocalDate date = LocalDate.now();
		databaseUser = new User();
		databaseUser.setUserid(1);
		databaseUser.setRole("nurse");
		databaseUser.setUsername("user1");
		databaseUser.setPassword("p4ssw0rd");
		databaseUser.setEmail("user1@email.com");
		databaseUser.setFirstName("user");
		databaseUser.setLastName("one");
		databaseUser.setDob(date);
		databaseUser.setAddress("123 Main St.");
		databaseUser.setProfile_image(null);
		databaseUser.setSpecialty("surgery");
		databaseUser.setAboutMe("hello");
		databaseUser.setViewPreference(true);
		databaseUser.setCovidStatus("none");
		
		changesUser = new User();
		changesUser.setUserid(1);
		changesUser.setViewPreference(true);
		changesUser.setCovidStatus("yes");
		uServ = new UserService(uRepo);
	}
	
	@Test
	public void contextProperlyLoads() throws Exception {
		assertThat(this.mockMvc).isNotNull();
	}
	
	@Test
	public void testUpdateUser() throws Exception {
		assertEquals(databaseUser.getCovidStatus(), "none");
		uServ.updateUser(databaseUser, changesUser);
		assertEquals(databaseUser.getCovidStatus(), "yes");
		assertEquals(databaseUser.getUsername(), "user1");
	}
	
}
