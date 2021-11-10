package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ReadMe.Main;
import com.example.model.User;
import com.example.service.UserService;

import lombok.NoArgsConstructor;

@RestController
@RequestMapping(value="/LifeSigns")
@CrossOrigin(origins="*")
@NoArgsConstructor
public class UserController {
	private UserService uServ;
	
	@Autowired
	public UserController(UserService uServ) {
		super();
		this.uServ = uServ;
	}
//GET: localhost:****/LifeSigns/Users	
@GetMapping("/Users")
public ResponseEntity<List<User>> getAllUsers() {
		return new ResponseEntity<List<User>>(uServ.getAllUsers(), HttpStatus.OK);
	}

//GET: localhost:***/LifeSigns/users/{username}
	@GetMapping("/users/{username}")
	public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username){
		User user = uServ.getUserByUsername(username);
		if(user==null) {
	
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
//DELETE: localhost:****/LifeSigns/users/{username}
		@DeleteMapping("/users/{username}")
		public ResponseEntity<String> deleteUser(@PathVariable("username") String username){
			User user = uServ.getUserByUsername(username);
			if(user==null) {
				//Main.log.warn("Failed to delete user with username " + username + ": No user of that name was found");
				return new ResponseEntity<String>("No user of that name was found", HttpStatus.NOT_FOUND);
			}
			
			uServ.deleteUser(user);
			//Main.log.info("Deleted user with username " + username + " from database");
			return new ResponseEntity<String>("User was deleted", HttpStatus.OK);

		}
//POST: localhost:***/LifeSigns/login
//Include user in JSON format in the request body
		@PostMapping("/login")
		public ResponseEntity<Object> validateUser(@RequestBody User user){
			User userList = user.getUsername();
			if() {return new ResponseEntity<>(uServ.getUserByUsername(user.getUsername()), HttpStatus.ACCEPTED);
				}	
			return new ResponseEntity<>("Invalid login", HttpStatus.FORBIDDEN);
		}
//POST: localhost:***/LifeSigns/register
//Include user in JSON format in the request body			
	
}

