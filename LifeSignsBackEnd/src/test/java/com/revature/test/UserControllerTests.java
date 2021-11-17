package com.revature.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.User;
import com.revature.service.UserService;

@SpringBootTest
public class UserControllerTests {

	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext context;
	
	@MockBean
	private UserService uServ;
    
	// databaseUser will hold how the database stores the user. AKA, the password is hashed in the database.
	private User databaseUser;

	// Holds a map for expectedInputJSON. We use this to shorten the code for tests when sending content to
	// the controller. It will turn the map into a JSON which is how the front end sends the back end information.
	Map<String, Object> expectedInputJSON = new HashMap<>();
	
	@BeforeEach
	public void setUp() throws Exception{
		this.mockMvc = webAppContextSetup(context).build();
		
		LocalDate date = LocalDate.now();
		expectedInputJSON.put("userid", 1);
		expectedInputJSON.put("role", "nurse");
		expectedInputJSON.put("username", "user1");
		expectedInputJSON.put("password", "p4ssw0rd");
		expectedInputJSON.put("email", "user1@email.com");
		expectedInputJSON.put("firstName", "user");
		expectedInputJSON.put("lastName", "one");
		expectedInputJSON.put("dob", date.toString());
		expectedInputJSON.put("address", "123 Main St.");
		expectedInputJSON.put("profile_image", null);
		expectedInputJSON.put("specialty", "surgery");
		expectedInputJSON.put("aboutMe", "hello");
		expectedInputJSON.put("viewPreference", true);
		expectedInputJSON.put("covidStatus", "none");
		
		
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
	}
    
	@Test
	public void contextProperlyLoads() throws Exception {
		assertThat(this.mockMvc).isNotNull();
	}
	
	@Test
	public void getUserByUserIdReturnNotFoundIfNull() throws Exception {
		when(uServ.getUserByUserId(0)).thenReturn(null);
		this.mockMvc.perform(get("/LifeSigns/user/id/0"))
			.andExpect(status().isNotFound());
	}
	
	@Test
	public void getUserByUserIdReturnOKIfFound() throws Exception {
		when(uServ.getUserByUserId(1)).thenReturn(databaseUser);
		this.mockMvc.perform(get("/LifeSigns/user/id/1"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.username").value("user1"));
	}
	
	@Test
	public void ensureGetUserByUserIdPostDoesntExist() throws Exception {
		this.mockMvc.perform(post("/LifeSigns/user/id/1"))
			.andExpect(status().isMethodNotAllowed());
	}
    
	@Test
	public void getUserByUsernameReturnNotFoundIfNull() throws Exception {
		when(uServ.getUserByUsername("noName")).thenReturn(null);
		this.mockMvc.perform(get("/LifeSigns/user/username/noName"))
			.andExpect(status().isNotFound());
	}
	
	@Test
	public void getUserByUsernameReturnOKIfFound() throws Exception {
		when(uServ.getUserByUsername("user1")).thenReturn(databaseUser);
		this.mockMvc.perform(get("/LifeSigns/user/username/user1"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.userid").value(1));
	}
	
	@Test
	public void ensureGetUserByUsernamePostDoesntExist() throws Exception {
		this.mockMvc.perform(post("/LifeSigns/user/username/user1"))
			.andExpect(status().isMethodNotAllowed());
	}
	
	@Test
	public void insertUserReturnForbiddenIfUserExists() throws Exception {
		when(uServ.getUserByUserId(1)).thenReturn(databaseUser);
		this.mockMvc.perform(post("/LifeSigns/user/insert")
			.content(asJSONString(expectedInputJSON))
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isForbidden());
	}
	
	@Test
	public void insertUserReturnCreatedIfUserDoesntExist() throws Exception {
		when(uServ.getUserByUserId(1)).thenReturn(null);
		when(uServ.getUserByUsername("user1")).thenReturn(null);
		when(uServ.getUserByEmail("user1@email.com")).thenReturn(null);
		this.mockMvc.perform(post("/LifeSigns/user/insert")
			.content(asJSONString(expectedInputJSON))
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.username").value("user1"));
	}
	
	@Test
	public void ensureInsertUserGetDoesntExist() throws Exception {
		this.mockMvc.perform(get("/LifeSigns/user/insert"))
			.andExpect(status().isMethodNotAllowed());
	}
	
	public static String asJSONString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
