package com.revature.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
class LoginTests {
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext context;
	@Autowired
	private BcryptPasswordEncoder passwordEncoder;
	
    @MockBean
    private UserService uServ;
    
    // databaseUser will hold how the database stores the user. AKA, the password is hashed in the database.
    private User databaseUser;

    // Holds a map for expectedInputJSON. We use this to shorten the code for tests when sending content to
    // the controller. It will turn the map into a JSON which is how the front end sends the back end information.
	Map<String, String> expectedInputJSON = new HashMap<>();
	
    @BeforeEach
    public void setUp() throws Exception{
		this.mockMvc = webAppContextSetup(context).build();
		String rawPassword = "password1";
		String encodedPassword = passwordEncoder.encode(rawPassword);
		
		expectedInputJSON.put("username", "user1");
		expectedInputJSON.put("password", rawPassword);
		expectedInputJSON.put("email", "user@gmail.com");
		expectedInputJSON.put("roleID", "0");
		
        databaseUser = new User("nurse", "user1",encodedPassword,"user@gmail.com");
    }
    
    /*
     * Ideas for why tests might fail.
     * 1) Have the endpoints changed? Have the method names for attached mappings changed?
     * 2) 401 error possibly means spring security is blocking all unauthorized connections. Make sure 
     *  WebSecurityConfigurerAdapter is properly overriden and allows connections to endpoints.
     *  3) Have expected status return codes changed?
     *  4) Have the models changed? More fields might need to be added to expectedInputJSON if we expect
     *  more JSON fields to be returned.
     */	
    
    @Test
 	public void contextProperlyLoads() throws Exception {
 		assertThat(this.mockMvc).isNotNull();
 	}
    
//    @Test 
//    public void loginControllerReturnInvalidIfNotFound() throws Exception{
//        when(uServ.getUserByUsername("ThereIsNoUserWithThisUsername")).thenReturn(null);
//        this.mockMvc.perform(post("/LifeSigns/login")
//    		.content(asJSONString(expectedInputJSON))
//			.contentType(MediaType.APPLICATION_JSON)
//			.accept(MediaType.APPLICATION_JSON))
//        	.andExpect(status().isUnauthorized());
//    }
    
//    @Test 
//    public void loginControllerReturnValidIfFound() throws Exception{
//        when(uServ.getUserByUsername("user1")).thenReturn(databaseUser);
//        this.mockMvc.perform(MockMvcRequestBuilders.post("/LifeSigns/login")
//				.content(asJSONString(expectedInputJSON))
//				.contentType(MediaType.APPLICATION_JSON)
//				.accept(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk());
//    }
//    
//    @Test 
//    public void loginControllerReturnInvalidIfWrongPassword() throws Exception{
//        when(uServ.getUserByUsername("user1")).thenReturn(databaseUser);
//		expectedInputJSON.put("password", "JustPutAWrongPasswordInHere");
//        this.mockMvc.perform(MockMvcRequestBuilders.post("/LifeSigns/login")
//				.content(asJSONString(expectedInputJSON))
//				.contentType(MediaType.APPLICATION_JSON)
//				.accept(MediaType.APPLICATION_JSON))
//    			.andExpect(status().isUnauthorized());
//    }
//    
//    @Test 
//    public void registerReturnConflictIfDuplicateUsername() throws Exception{
//        when(uServ.getUserByUsername("user1")).thenReturn(databaseUser);
//        this.mockMvc.perform(MockMvcRequestBuilders.post("/LifeSigns/register")
//				.content(asJSONString(expectedInputJSON))
//				.contentType(MediaType.APPLICATION_JSON)
//				.accept(MediaType.APPLICATION_JSON))
//    			.andExpect(status().isConflict());
//    }
//    
//    @Test 
//    public void registerReturnCreatedIfValidUser() throws Exception{
//        when(uServ.getUserByUsername("user1")).thenReturn(null);
//        this.mockMvc.perform(MockMvcRequestBuilders.post("/LifeSigns/register")
//				.content(asJSONString(expectedInputJSON))
//				.contentType(MediaType.APPLICATION_JSON)
//				.accept(MediaType.APPLICATION_JSON))
//    			.andExpect(status().isCreated());
//    }

    @Test 
    public void ensureRegisterGetDoesntExist() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.get("/LifeSigns/register")
				.content(asJSONString(expectedInputJSON))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
    			.andExpect(status().isMethodNotAllowed());
    }

    @Test 
    public void ensureLoginGetDoesntExist() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.get("/LifeSigns/login")
				.content(asJSONString(expectedInputJSON))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
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