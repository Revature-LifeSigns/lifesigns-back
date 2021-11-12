package com.revature.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.service.UserService;

@RestController
@RequestMapping(value = "/unit")
@CrossOrigin(origins = "*")
public class UnitController {
	private UserService uServ;
	
	@Autowired
	public UnitController(UserService uServ) {
		super();
		this.uServ = uServ;
	}
	
	
}
