package com.example.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.revature.controller.*;
import com.revature.model.User;
import com.revature.service.UserService;
import com.revature.repository.*;
import com.revature.util.*;

@SpringBootTest(classes=(UserService.class))
public class UserServiceTests {
	
	//Mock classes, methods, and variables
	
	@MockBean
	private UserService uServ;
	
	@Mock
	private UserService testServ;
	
	@Mock
	private User testUser;
	
	@Mock
	private List<User> allUsers;
	
	@Mock
	private List<User> listUser;
	
	@MockBean
	private UserRepository uRepo;
	

	@BeforeEach
	public void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
		uServ = new UserService(uRepo);
		testUser = new User(2, 2, "user1", "password1", "test@test.com");
		listUser.add(testUser);
		allUsers = uServ.getAllUsers();
		when(uRepo.findByUsername("user1")).thenReturn(testUser);
		when(uRepo.findAll()).thenReturn(allUsers);
	}
	
	@Test
	public void testInsertUser() {
		testServ.insertUser(testUser);
		verify(testServ).insertUser(testUser);
	}
	
	@Test
	public void testGetUserByUsernameSuccess() {
		assertEquals(uServ.getUserByUsername("user1"), testUser);
	}
	
	@Test
	public void testGetUserByUsernameFailure() {
		assertEquals(uServ.getUserByUsername("user8"), null);
	}
	
	@Test
	public void testGetAllUsers() {
		assertEquals(uServ.getAllUsers(), allUsers);
	}
	
	@Test
	public void testGetUserByRoleID() {
		assertEquals(testServ.getUserByRoleId(testUser.getRoleid()), listUser.get(0));
	}
	
	@Test
	public void testDeleteUser() {
		testServ.deleteUser(testUser);
		verify(testServ).deleteUser(testUser);
	}

}
