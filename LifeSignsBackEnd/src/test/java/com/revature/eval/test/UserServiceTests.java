package com.revature.eval.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
	private User databaseUser, changesUser, identicalUser;
	
	@BeforeEach
	public void setUp() throws Exception {
		this.mockMvc = webAppContextSetup(context).build();
		
		databaseUser = new User();
		initializeUser(databaseUser);
		
		identicalUser = new User();
		initializeUser(identicalUser);
		
		changesUser = new User();
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
		assertEquals(databaseUser, identicalUser);
		uServ.updateUser(databaseUser, changesUser);
		assertEquals(databaseUser.getCovidStatus(), "yes");
		assertEquals(databaseUser.getUsername(), "user1");
		assertNotEquals(databaseUser, identicalUser);
	}
	
	@Test
	public void testUpdateUserNoChanges() throws Exception {
		assertEquals(databaseUser, identicalUser);
		uServ.updateUser(databaseUser, new User());
		assertEquals(databaseUser, identicalUser);
	}
	
	private void initializeUser(User user) {
		user.setUserid(1);
		user.setRole("nurse");
		user.setUsername("user1");
		user.setPassword("p4ssw0rd");
		user.setEmail("user1@email.com");
		user.setFirstName("user");
		user.setLastName("one");
		user.setDob(LocalDate.parse("1970-01-01"));
		user.setAddress("123 Main St.");
		user.setProfile_image(null);
		user.setSpecialty("surgery");
		user.setAboutMe("hello");
		user.setViewPreference(false);
		user.setCovidStatus("none");
	}
	
}
