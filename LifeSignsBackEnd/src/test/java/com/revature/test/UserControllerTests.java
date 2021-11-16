package com.revature.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.User;
import com.revature.service.UserService;
import com.revature.util.BcryptPasswordEncoder;

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
		
		expectedInputJSON.put("userid", 1);
		expectedInputJSON.put("username", "user1");
		
		databaseUser = new User(1, null, "user1", null, null, null, null, null, null, null, null, null, null);
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
	
	public static String asJSONString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
