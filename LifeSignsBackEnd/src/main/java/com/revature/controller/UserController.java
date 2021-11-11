package com.revature.controller;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.User;
import com.revature.service.UserService;
import com.revature.util.BcryptPasswordEncoder;


@RestController
@RequestMapping(value = "/LifeSigns")
@CrossOrigin(origins = "*")
public class UserController {
    private UserService uServ;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService uServ, BcryptPasswordEncoder BCryptHasher) {
        super();
        this.uServ = uServ;
        this.passwordEncoder = BCryptHasher.getPasswordEncoder();
    }

    //POST: localhost:***/LifeSigns/login
    //Include user in JSON format in the request body
    @PostMapping("/login")
    public ResponseEntity < Object > validateUser(@RequestBody LinkedHashMap < String, String > userMap) {
        // Right now, the user should contain the unhashed password stored in the user object.
        // Then the database should have the BCrypt hashed version of the password and we'll check those.
        User returnedUser = uServ.getUserByUsername(userMap.get("username"));
        if (returnedUser == null) {
            return new ResponseEntity < > ("Invalid login", HttpStatus.FORBIDDEN);
        }
        if (passwordEncoder.matches(userMap.get("password"), returnedUser.getPassword())) {
            return new ResponseEntity < > (uServ.getUserByUsername(userMap.get("username")), HttpStatus.ACCEPTED);
        }
        return new ResponseEntity < > ("Invalid login", HttpStatus.FORBIDDEN);
    }

    //POST: localhost:***/LifeSigns/register
    //Include user in JSON format in the request body
    @PostMapping(value = "/register")
    public ResponseEntity < Object > newUser(@RequestBody LinkedHashMap < String, String > userMap) {
        User returnedUser = uServ.getUserByUsername(userMap.get("username"));
        if (returnedUser != null)
            return new ResponseEntity < > ("Username is taken", HttpStatus.FORBIDDEN);
        //using the constructor User(int roleID, String username, String password, String email), roleID must be parsed to int since the request body returns a hashmap of strings.
        User newUser = new User(Integer.parseInt(userMap.get("roleID")), userMap.get("username"),
            passwordEncoder.encode(userMap.get("password")), userMap.get("email"));
        uServ.insertUser(newUser);
        return new ResponseEntity < > (newUser, HttpStatus.ACCEPTED);
    }
}
